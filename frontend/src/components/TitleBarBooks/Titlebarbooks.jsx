import { useState } from 'react';
import styles from './Titlebarbooks.module.css';
import Modaladdbook from '../ModalAddBook/Modaladdbook';

function Titlebarbooks({Title, buttonTitle}) {

  const [isModalOpen, setIsModalOpen] = useState(false);

  const openModal = () => {
    setIsModalOpen(true);
  };

  const closeModal = () => {
    setIsModalOpen(false);
  };

  return (
    <div className={styles.titleContainer}>
      <h1>{Title}</h1>
      <button 
        className={styles.addButton}
        onClick={openModal}
      >
        {buttonTitle}
      </button>
      <Modaladdbook isOpen={isModalOpen} closeModal={closeModal} />
    </div>
  )
}

export default Titlebarbooks
