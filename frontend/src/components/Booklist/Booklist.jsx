import styles from './Booklist.module.css';
import { MdEdit } from "react-icons/md";
import { MdDelete } from "react-icons/md";

function Booklist({id, title, author, isbn, category, publishDate}) {
  return (
    <div className={styles.booklistContainer}>
      <div className={styles.id}><span>{id}</span></div>
      <div className={styles.title}><span>{title}</span></div>
      <div className={styles.author}><span>{author}</span></div>
      <div className={styles.isbn}><span>{isbn}</span></div>
      <div className={styles.category}><span>{category}</span></div>
      <div className={styles.date}><span>{publishDate}</span></div>
      <div className={styles.buttons}>
        <MdEdit /> 
        <MdDelete />
      </div>
    </div>
  )
}

export default Booklist
