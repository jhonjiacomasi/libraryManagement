import styles from './Loanlist.module.css';
import { MdEdit } from "react-icons/md";
import { MdDelete } from "react-icons/md";

function Loanlist({id, name, title, loandate, returndate}) {
  
  return (
    <div className={styles.loanlistContainer}>
      <div className={styles.id}><span>{id}</span></div>
      <div className={styles.name}><span>{name}</span></div>
      <div className={styles.title}><span>{title}</span></div>
      <div className={styles.loandate}><span>{loandate}</span></div>
      <div className={styles.returndate}><span>{returndate}</span></div>
      <div className={styles.buttons}>
        <MdEdit /> 
        <MdDelete />
      </div>
    </div>
  )
}

export default Loanlist
