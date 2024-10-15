import { IoIosList } from "react-icons/io";
import { FiUser } from "react-icons/fi";
import { IoLibrary } from "react-icons/io5";
import styles from './Home.module.css';
import Sidebar from '../../components/Sidebar/Sidebar';
import Searchbar from '../../components/Searchbar/Searchbar';
import Card from '../../components/Card/Card';
import Books from '../../components/Books/Books';
import { Link } from "react-router-dom";
import { useState } from "react";

function Home() {

  const [books, setBooks] = useState([]);
  
  return (
    <div className={styles.mainContainer}>
      <div className={styles.sidebar}> 
        <Sidebar />
      </div>
      <div className={styles.main}> 
        <div className={styles.searchbar}> 
          <Searchbar setBooks={setBooks}/>
        </div>
        <div className={styles.cards}> 
          <Link to="/users">
            <Card icon={<FiUser />} title="Users" />
          </Link>
          <Link to="/books">
            <Card icon={<IoLibrary />} title="Books" />
          </Link>
          <Link to="/loan">
            <Card icon={<IoIosList />} title="Loan" />
          </Link>
        </div>
        <div className={styles.booksContainer}>
          <div className={styles.title}>
            <h2>Recommendation</h2>
          </div>
          <div className={styles.booksCards}>
            <Books books={books}/>
          </div> 
        </div>
      </div>
    </div>
  );
}

export default Home;