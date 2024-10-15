import { useState } from 'react';
import axios from 'axios';
import styles from './Modaladdbook.module.css';

function Modaladdbook({ isOpen, closeModal }) {

  const [title, setTitle] = useState('');
  const [author, setAuthor] = useState('');
  const [isbn, setIsbn] = useState('');
  const [category, setCategory] = useState('');
  const [publishDate, setPublishDate] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();
    const newBook = {
      title,
      author,
      isbn,
      category,
      publishDate,
    };
    try {
      const response = await axios.post('http://localhost:8080/v1/savebook', newBook);
      console.log(response);
      setTitle('');
      setAuthor('');
      setIsbn('');
      setCategory('');
      setPublishDate('');
    } catch (error) {
      console.error(error);
    }
  };

  if (!isOpen) return null;

  return (
    <div className={styles.modalBook}>
      <form onSubmit={handleSubmit}>
        <div>
          <label className={styles.label}>Title:</label>
          <input 
            className={styles.input}
            type="text"
            value={title}
            onChange={(e) => setTitle(e.target.value)}
          />
          <label className={styles.label}>Author:</label>
          <input 
            className={styles.input}
            type="text"
            value={author}
            onChange={(e) => setAuthor(e.target.value)}
          />
          <label className={styles.label}>ISBN:</label>
          <input 
            className={styles.input}
            type="text"
            value={isbn}
            onChange={(e) => setIsbn(e.target.value)}
          />
          <label className={styles.label}>Category:</label>
          <input 
            className={styles.input}
            type="text"
            value={category}
            onChange={(e) => setCategory(e.target.value)}
          />
          <label className={styles.label}>Published Date:</label>
          <input 
            className={styles.input}
            type="text"
            value={publishDate}
            onChange={(e) => setPublishDate(e.target.value)}
          />
        </div>
        <div className={styles.buttons}>
          <button className={styles.buttonsStyle} type="submit">ADD</button>
          <button className={styles.buttonsStyle} type="button" onClick={closeModal}>CANCEL</button>
        </div>
      </form>
    </div>
  );
}

export default Modaladdbook;
