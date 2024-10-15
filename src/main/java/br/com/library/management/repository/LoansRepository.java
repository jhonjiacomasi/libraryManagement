package br.com.library.management.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.library.management.model.Loans;

@Repository
public interface LoansRepository  extends JpaRepository<Loans, UUID>{

	boolean existsByBooksIdAndStatusTrue(UUID bookId);
}
