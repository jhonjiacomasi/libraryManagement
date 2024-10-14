package com.gestao.biblioteca.recomendacoes.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestao.biblioteca.recomendacoes.dto.LoansDto;
import com.gestao.biblioteca.recomendacoes.model.Loans;

@Repository
public interface LoansRepository  extends JpaRepository<Loans, UUID>{
}
