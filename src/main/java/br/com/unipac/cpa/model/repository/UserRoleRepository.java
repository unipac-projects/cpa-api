package br.com.unipac.cpa.model.repository;

import br.com.unipac.cpa.model.domain.Role;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier(value = "userRoleRepository")
public interface UserRoleRepository extends JpaRepository<Role, Long> {
	Role findByRole(@Param("role") String role);
}