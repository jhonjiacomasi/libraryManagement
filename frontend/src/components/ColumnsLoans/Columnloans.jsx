import styles from './Columnloans.module.css';

function Columnloans() {
  return (
    <div className={styles.columnsContainer}>
       <div className={styles.id}><span>ID</span></div>
       <div className={styles.name}><span>NAME</span></div>
       <div className={styles.title}><span>TITLE</span></div>
       <div className={styles.dateloan}><span>LOAN DATE</span></div>
       <div className={styles.datereturn}><span>RETURN DATE</span></div>
    </div>
  )
}

export default Columnloans
