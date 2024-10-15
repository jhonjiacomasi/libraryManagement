package br.com.library.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.library.management.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
