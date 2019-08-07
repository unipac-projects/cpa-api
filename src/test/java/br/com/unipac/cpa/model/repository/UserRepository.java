package br.com.unipac.cpa.model.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.unipac.cpa.model.domain.User;

@Repository
@Qualifier(value = "userRepository")
public interface UserRepository extends JpaRepository<User, Long> {
	
    User findByEmail(String email);
    User findByEmailAndPassword(String email, String password);
}