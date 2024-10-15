import Sidebar from "../../components/Sidebar/Sidebar";
import Searchbar from "../../components/Searchbar/Searchbar";
import Titlebarbooks from "../../components/TitleBarBooks/Titlebarbooks";
import Booklist from "../../components/Booklist/Booklist";
import styles from "./Books.module.css";
import Columnsbooks from "../../components/ColumnsBooks/Columnsbooks";
import { useEffect, useState } from "react";
import axios from "axios";

function Books() {

  const [books, setBooks] = useState([]);

  useEffect(() => {
    const fetchBooks = async () => {
      try {
        const response = await axios.get('http://localhost:8080/v1/allBooks');
        setBooks(response.data);
        console.log(response)
      } catch (error) {
        console.error('Error:', error);
      }
    };
    fetchBooks();
  }, []);

  return (
    <div className={styles.mainContainer}>
      <div className={styles.sidebar}> 
        <Sidebar />
      </div>
      <div className={styles.main}> 
        <div className={styles.searchbar}> 
          <Searchbar />
        </div>
        <div className={styles.titlebar}> 
          <Titlebarbooks Title="Books" buttonTitle={"ADD BOOK"}/>
        </div>
        <div className={styles.columnsContainer}>
          <Columnsbooks />
        </div>
        <div className={styles.userlistContainer}>
          {books.map((book) => (
            <Booklist key={book.id} id={null} title={book.title} author={book.author} isbn={book.isbn} category={book.category} publishDate={book.publishDate} />
          ))}
        </div>
      </div>
    </div>
  );
}

export default Books