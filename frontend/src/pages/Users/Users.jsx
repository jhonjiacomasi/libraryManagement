import Sidebar from '../../components/Sidebar/Sidebar';
import Searchbar from '../../components/Searchbar/Searchbar';
import axios from 'axios';
import styles from './Users.module.css';
import Userlist from '../../components/Userlist/Userlist';
import Columnsusers from '../../components/ColumnsUsers/Columnsusers';
import { useEffect, useState } from 'react';
import Titlebaruser from '../../components/TitleBarUser/Titlebaruser';

function Users() {

  const [users, setUsers] = useState([]);

  useEffect(() => {
    const fetchUsers = async () => {
      try {
        const response = await axios.get('http://localhost:8080/v1/allUsers');
        setUsers(response.data);
        console.log(response.data);
      } catch (error) {
        console.error('Error:', error);
      }
    };
    fetchUsers();
  }, [users]);

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
          <Titlebaruser Title="Users" buttonTitle={'ADD USER'}/>
        </div>
        <div className={styles.columnsContainer}>
          <Columnsusers />
        </div>
        <div className={styles.userlistContainer}>
          {users.map((user) => (
            <Userlist key={user.id} id={user.id} user={user.name} email={user.email} telephone={user.telephone} date={user.registrationdate} />
          ))}
        </div>
      </div>
    </div>
  );
}

export default Users