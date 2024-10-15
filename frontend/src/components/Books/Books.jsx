import styles from './Books.module.css';
import { Swiper, SwiperSlide } from 'swiper/react';
import { Pagination } from 'swiper/modules';
import axios from 'axios';
import 'swiper/css';
import 'swiper/css/pagination';
import 'swiper/css/navigation';

function Books({ books }) {
  
  if (books.length === 0) {
    return <div>No books found</div>;
  }

  const handleSubmit = async (book) => {
    const newBook = {
      title: book.volumeInfo.title, 
      author: book.volumeInfo.authors?.join(', '),
      isbn: book.volumeInfo.industryIdentifiers[0]?.identifier || 'N/A', // Fallback se não houver ISBN
      category: book.volumeInfo.categories?.join(', ') || 'N/A',
      publishDate: book.volumeInfo.publishedDate || 'N/A',
    };

    console.log(newBook);

    try {
      const response = await axios.post('http://localhost:8080/v1/savebook', newBook);
      console.log(response);
    } catch (error) {
      console.error('Error saving the book:', error);
    }
  };

  return (
    <div className={styles.booksContainer}>
      <Swiper
        slidesPerView={5} 
        spaceBetween={0} 
        pagination={{ clickable: true }}
        modules={[Pagination]}
      >
        {books.map((book) => (
          <SwiperSlide key={book.id}>
            <div className={styles.books}>
              <div className={styles.bookCover}>
                <img src={book.volumeInfo.imageLinks?.thumbnail} alt={book.volumeInfo.title} />
              </div>
              <div className={styles.bookInfo}>
                <h2>{book.volumeInfo.title}</h2>
                <span>{book.volumeInfo.authors?.join(', ')}</span>
              </div>
              <div className={styles.buttonContainer}>
                <button 
                  className={styles.cardButton} 
                  onClick={() => handleSubmit(book)}
                >
                  ADD BOOK
                </button>
              </div>
              
            </div>
          </SwiperSlide>
        ))}
      </Swiper>
    </div>
  );
}

export default Books;
