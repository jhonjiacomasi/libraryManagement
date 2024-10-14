package com.gestao.biblioteca.recomendacoes.dto;

import java.util.UUID;

public record LoansDto	(
						 Long user_id,
						 UUID books_id,
						 String loansDate,
						 String returnDate,
						 Boolean status) {}

