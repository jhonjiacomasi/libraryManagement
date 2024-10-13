package com.gestao.biblioteca.recomendacoes.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestao.biblioteca.recomendacoes.model.Loans;

public interface LoansRepository  extends JpaRepository<Loans, UUID>{

}
