package br.com.unipac.cpa.repository;

import br.com.unipac.cpa.model.domain.Role;
import br.com.unipac.cpa.model.domain.User;
import br.com.unipac.cpa.model.repository.UserRepository;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@ImportAutoConfiguration(exclude = FlywayAutoConfiguration.class)
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void should_find_no_Users_if_repository_is_empty() {
        Iterable<User> seeds = userRepository.findAll();
        assertThat(seeds).isEmpty();
    }

    private User getUser() {

        return User.builder()
                .email("Bradesco")
                .password("root@localhost")
                .roles(getRoles())
                .build();
    }

    private Set<Role> getRoles() {
        Set<Role> roles = new HashSet<>();

        Role role = new Role();
        role.setRole("ADMIN");
        roles.add(role);

        Role role1 = new Role();
        role1.setRole("USER");
        roles.add(role1);

        return roles;
    }

    @Test
    public void should_store_a_User() {
        User user = getUser();
        User User = userRepository.save(user);

        assertThat(User).hasFieldOrPropertyWithValue("name","Everis");
        assertThat(User).hasFieldOrPropertyWithValue("mobile","9999999999");
        assertThat(User).hasFieldOrPropertyWithValue("roles", getRoles());
    }

    @Test(expected = ConstraintViolationException.class)
    public void should_throw_constraint_violation_execption_User_is_null() {
        thrown.expect(ConstraintViolationException.class);
        thrown.expectMessage("The field User is required");
        thrown.expectMessage("must not be null");
        User User = new User( "root@localhost","3499999999",getRoles());

        entityManager.persistAndFlush(User);
    }

    @Test
    public void should_found_store_a_User() {
        User user = getUser();
        entityManager.persist(user);

        Optional<User> found = userRepository.findById(getUser().getId());
        assertThat(found.get()).isEqualTo(user);
    }

    @Test
    public void should_found_null_User() {
        Optional<User> fromDb = userRepository.findById(1l);
        assertThat(fromDb.get()).isNull();
    }

    @Test
    public void whenFindById_thenReturnUser() {
        User user = getUser();
        entityManager.persistAndFlush(user);

        User fromDb = userRepository.findById(user.getId()).orElse(null);
        assertThat(fromDb).isEqualTo(user);
    }

    @Test
    public void whenInvalidId_thenReturnNull() {
        User fromDb = userRepository.findById(-11l).orElse(null);
        assertThat(fromDb).isNull();
    }

    @Test
    public void givenSetOfUsers_whenFindAll_thenReturnAllUsers() {
        User user = getUser();
        User user1 = getUser();
        User user2 = getUser();

        entityManager.persist(user);
        entityManager.persist(user1);
        entityManager.persist(user2);
        entityManager.flush();

        Iterator<User> allUsers = userRepository.findAll().iterator();
        List<User> users = new ArrayList<>();
        allUsers.forEachRemaining(c -> users.add(c));

        assertThat(allUsers).hasSize(3);//.extracting(User::getUser).containsOnly(user.getUser(), User2.getUser(), User3.getUser());
    }
}
