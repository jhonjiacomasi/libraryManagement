package com.gestao.biblioteca.recomendacoes.controller;

import java.io.Serializable;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestao.biblioteca.recomendacoes.dto.LoansDto;
import com.gestao.biblioteca.recomendacoes.model.Loans;
import com.gestao.biblioteca.recomendacoes.service.LoanService;
@RestController
@RequestMapping("/v1")
public class LoansController implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private final LoanService loanService;
	

	public LoansController(LoanService loanService) {
		super();
		this.loanService = loanService;
	}



	@PostMapping("/saveloans")
	public ResponseEntity<Loans> saveLoans(@RequestBody LoansDto loansDto) {

		return ResponseEntity.status(HttpStatus.CREATED).body(loanService.saveLoans(loansDto));
	}
	
	
	
//	
}
