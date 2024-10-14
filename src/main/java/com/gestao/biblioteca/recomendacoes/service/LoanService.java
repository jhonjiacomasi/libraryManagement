package com.gestao.biblioteca.recomendacoes.service;

import org.springframework.stereotype.Service;

import com.gestao.biblioteca.recomendacoes.dto.LoansDto;
import com.gestao.biblioteca.recomendacoes.model.Loans;
import com.gestao.biblioteca.recomendacoes.repository.BooksRepository;
import com.gestao.biblioteca.recomendacoes.repository.LoansRepository;
import com.gestao.biblioteca.recomendacoes.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoanService {
	
	private final LoansRepository loansRepository;
	
	private final UserRepository userRepository;
	
	private final BooksRepository booksRepository;
	
	
	
	@Transactional
	public Loans saveLoans(LoansDto loansDto) {
		Loans loans = new Loans(loansDto);
		loans.setUser(userRepository.findById(loansDto.user_id()).get());
		loans.setBooks(booksRepository.findById(loansDto.books_id()).get());
		return loansRepository.save(loans);
	}
	

}
