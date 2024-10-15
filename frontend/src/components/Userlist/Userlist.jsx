import styles from './Userlist.module.css';
import { MdEdit } from "react-icons/md";
import { MdDelete } from "react-icons/md";

function Userlist({id, user, email, telephone, date}) {
  return (
    <div className={styles.userlistContainer}>
       <div className={styles.id}><span>{id}</span></div>
       <div className={styles.name}><span>{user}</span></div>
       <div className={styles.email}><span>{email}</span></div>
       <div className={styles.telephone}><span>{telephone}</span></div>
       <div className={styles.date}><span>{date}</span></div>
       <div className={styles.buttons}>
        <MdEdit /> 
        <MdDelete />
      </div>
    </div>
  )
}

export default Userlist
