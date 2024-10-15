import React, { useState } from 'react';
import axios from 'axios';
import styles from './Modaladduser.module.css'; 

function Modaladduser({ isOpen, closeModal }) {
  const [name, setName] = useState('');
  const [email, setEmail] = useState('');
  const [telephone, setTelephone] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();
    const newUser = {
      name,
      email,
      telephone,
    };
    try {
      const response = await axios.post('http://localhost:8080/v1/saveUser', newUser);
      console.log(response);
      setName('');
      setEmail('');
      setTelephone('');
    } catch (error) {
      console.error(error);
    }
  };

  if (!isOpen) return null;

  return (
    <div className={styles.modalUser}>
      <form onSubmit={handleSubmit}>
        <div>
          <label className={styles.label}>Name:</label>
          <input 
            className={styles.input}
            type="text"
            value={name}
            onChange={(e) => setName(e.target.value)}
          />
          <label className={styles.label}>Email:</label>
          <input 
            className={styles.input}
            type="text"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />
          <label className={styles.label}>Phone:</label>
          <input 
            className={styles.input}
            type="text"
            value={telephone}
            onChange={(e) => setTelephone(e.target.value)}
          />
        </div>
        <div className={styles.buttons}>
          <button className={styles.buttonsStyle} type="submit">ADD</button>
          <button className={styles.buttonsStyle} type="button" onClick={closeModal}>CANCEL</button>
        </div>
      </form>
    </div>
  );
}

export default Modaladduser;