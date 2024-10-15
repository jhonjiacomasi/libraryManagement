import styles from './Columnsbooks.module.css';

function Columnsbooks() {
  return (
    <div className={styles.columnsContainer}>
      <div className={styles.id}><span>ID</span></div>
      <div className={styles.name}><span>TITLE</span></div>
      <div className={styles.email}><span>AUTHOR</span></div>
      <div className={styles.telephone}><span>ISBN</span></div>
      <div className={styles.category}><span>CATEGORY</span></div>
      <div className={styles.date}><span>DATE</span></div>
    </div>
  )
}

export default Columnsbooks