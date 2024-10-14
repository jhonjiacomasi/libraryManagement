package com.gestao.biblioteca.recomendacoes.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record LoansDto	(
						 Long user_id,
						 UUID books_id,
						 LocalDateTime loansDate,
						 LocalDateTime returnDate,
						 Boolean status) {}

