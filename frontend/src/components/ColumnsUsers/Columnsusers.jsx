import styles from './Columnsusers.module.css';

function Columnsusers() {
  return (
    <div className={styles.columnsContainer}>
       <div className={styles.id}><span>ID</span></div>
       <div className={styles.name}><span>NAME</span></div>
       <div className={styles.email}><span>EMAIL</span></div>
       <div className={styles.telephone}><span>TELEPHONE</span></div>
       <div className={styles.date}><span>DATE</span></div>
    </div>
  )
}

export default Columnsusers
