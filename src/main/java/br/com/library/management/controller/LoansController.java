package br.com.library.management.controller;

import java.io.Serializable;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.library.management.dto.LoansDto;
import br.com.library.management.model.Loans;
import br.com.library.management.service.LoanService;
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
}
