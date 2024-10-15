import { FiUser } from "react-icons/fi";
import { AiOutlineHome } from "react-icons/ai";
import { IoIosList } from "react-icons/io";
import { NavLink } from "react-router-dom";
import { IoLibrary } from "react-icons/io5";
import styles from './Sidebar.module.css';

function Sidebar() {
  return (
    <div className={styles.sidebarContainer}>
      <div className={styles.sidebarTitle}>
        <h2>Library</h2>
      </div>
      <div className={styles.sidebarLinks}>
        <NavLink 
          exact 
          to="/" 
          className={({ isActive }) => isActive ? `${styles.links} ${styles.active}` : styles.links}
        >
          <AiOutlineHome className={styles.icon}/>
          <h2>Home</h2>
        </NavLink>

        <NavLink 
          to="/users" 
          className={({ isActive }) => isActive ? `${styles.links} ${styles.active}` : styles.links}
        >
          <FiUser className={styles.icon}/>
          <h2>Users</h2>
        </NavLink>

        <NavLink 
          to="/books" 
          className={({ isActive }) => isActive ? `${styles.links} ${styles.active}` : styles.links}
        >
          <IoLibrary className={styles.icon}/>
          <h2>Books</h2>
        </NavLink>

        <NavLink 
          to="/loan" 
          className={({ isActive }) => isActive ? `${styles.links} ${styles.active}` : styles.links}
        >
          <IoIosList className={styles.icon}/>
          <h2>Loan</h2>
        </NavLink>
      </div>
    </div>
  );
}

export default Sidebar;