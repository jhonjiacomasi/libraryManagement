package com.gestao.biblioteca.recomendacoes.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestao.biblioteca.recomendacoes.model.Books;

public interface LibraryRepository extends JpaRepository<Books, UUID> {

}
