import React, { useState } from 'react';
import axios from 'axios';
import styles from './Searchbar.module.css';

function Searchbar({ setBooks }) {
  const [search, setSearch] = useState('');

  const searchBook = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.get(`https://www.googleapis.com/books/v1/volumes?q=${search}&key=AIzaSyCygQhR8ULbGW_qwBETxa59vyZhx5lj8A0`);
      setBooks(response.data.items);
      setSearch('');
    } catch (error) {
      console.error(error);
    }
  }

  return (
    <div className={styles.searchbarContainer}>
      <form onSubmit={searchBook}>
        <input 
          className={styles.searchInput} 
          type="text" 
          placeholder='Book Title' 
          value={search} 
          onChange={e => setSearch(e.target.value)}
        />
        <button type="submit" className={styles.searchButton}>Search</button>
      </form>
    </div>
  )
}

export default Searchbar;
