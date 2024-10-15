import styles from './Card.module.css';

function Card({icon, title}) {

  return (
    <div className={styles.cardContainer}>
      <div className={styles.cardInfo}>
        <div className={styles.cardIcon}>
          {icon}
        </div>
        <div className={styles.cardText}>
          <h2>{title}</h2>
        </div>
      </div>
      <div className={styles.cardData}>
        <h3>10</h3>
      </div>
    </div>
  )
}

export default Card
