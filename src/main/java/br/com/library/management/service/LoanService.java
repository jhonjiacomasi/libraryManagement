package br.com.library.management.service;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.com.library.management.dto.LoansDto;
import br.com.library.management.exception.LibraryManagementException;
import br.com.library.management.model.Books;
import br.com.library.management.model.Loans;
import br.com.library.management.repository.BooksRepository;
import br.com.library.management.repository.LoansRepository;
import br.com.library.management.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoanService {
	
	private final LoansRepository loansRepository;
	
	private final UserRepository userRepository;
	
	private final BooksRepository booksRepository;
	
	 private static final Logger logger = LoggerFactory.getLogger(LoanService.class);
	
		@Transactional
		public Loans saveLoans(LoansDto loansDto) {
			try {
				
				if(isBookLoaned(loansDto.books_id())) {
					throw new LibraryManagementException("Failed, Book is loaned: ");
				}
				Loans loans = new Loans(loansDto);
				loans.setUser(userRepository.findById(loansDto.user_id()).get());
				loans.setBooks(booksRepository.findById(loansDto.books_id()).get());
				logRecommendedBooks(recommendBooksByCategory(loans.getBooks().getCategory()));
				
				return loansRepository.save(loans);
				
			} catch (Exception e) {
				throw new LibraryManagementException("Failed to loaned in the database, Id exists", e);
			}
		}
		
		private boolean isBookLoaned(UUID bookId) {
			return loansRepository.existsByBooksIdAndStatusTrue(bookId);
		}
	
		private List<Books> recommendBooksByCategory(String category) {
			return booksRepository.findByCategory(category);
		}

		private void logRecommendedBooks(List<Books> books) {
			books.forEach(book -> logger.info("Recomendado: " + book.getTitle() + " por " + book.getAuthor()));
		}
    
    
    
		@Transactional
		public Loans updateLoans(UUID id, LoansDto loansDto) throws ParseException {
			Loans loans = loansRepository.findById(id)
					.orElseThrow(() -> new EntityNotFoundException("Loan Not found:" + id));

			LocalDate loansDate = LocalDate.parse(loansDto.loansDate());
			LocalDate returnDate = LocalDate.parse(loansDto.returnDate());

			if (returnDate.isBefore(loansDate)) {
				throw new IllegalArgumentException("Return date cannot be before loans date");
			}

			loans.setStatus(loansDto.status());
			loans.setReturnDate(loansDto.returnDate());
			return loansRepository.save(loans);
		}
	
	
	

}
