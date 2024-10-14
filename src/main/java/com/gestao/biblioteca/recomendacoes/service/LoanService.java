package com.gestao.biblioteca.recomendacoes.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.gestao.biblioteca.recomendacoes.dto.LoansDto;
import com.gestao.biblioteca.recomendacoes.model.Books;
import com.gestao.biblioteca.recomendacoes.model.Loans;
import com.gestao.biblioteca.recomendacoes.repository.BooksRepository;
import com.gestao.biblioteca.recomendacoes.repository.LoansRepository;
import com.gestao.biblioteca.recomendacoes.repository.UserRepository;

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
		Loans loans = new Loans(loansDto);
		loans.setUser(userRepository.findById(loansDto.user_id()).get());
		loans.setBooks(booksRepository.findById(loansDto.books_id()).get());
	
		logRecommendedBooks(recommendBooksByCategory(loans.getBooks().getCategory()));
		
		return loansRepository.save(loans);
	}
	
	private List<Books> recommendBooksByCategory(String category) {
        return booksRepository.findByCategory(category);
    }

    private void logRecommendedBooks(List<Books> books) {
        books.forEach(book -> 
        logger.info("Recomendado: " + book.getTitle() + " por " + book.getAuthor()));
    }
    
    
	@Transactional
	public Loans updateLoans(UUID id,LoansDto loansDto) throws ParseException {
		Loans loans = loansRepository.findById(id)
			.orElseThrow(() -> new EntityNotFoundException("Loan Not found:"+id));
		final SimpleDateFormat FORMATTER = new SimpleDateFormat("yyyy-MM-dd");
		
		Date loansDate = FORMATTER.parse(loansDto.loansDate());
        Date returnDate = FORMATTER.parse(loansDto.returnDate());

        if (returnDate.before(loansDate)) {
            throw new IllegalArgumentException("Return date cannot be before loans date");
        }
		
		loans.setStatus(loansDto.status());
		loans.setReturnDate(loansDto.returnDate());
		return loansRepository.save(loans);
	}
	
	
	
	

}
