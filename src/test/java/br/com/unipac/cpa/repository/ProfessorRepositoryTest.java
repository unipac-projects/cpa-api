package br.com.unipac.cpa.repository;

import br.com.unipac.cpa.model.domain.Professor;
import br.com.unipac.cpa.model.domain.Company;
import br.com.unipac.cpa.model.domain.DocumentRegion;
import br.com.unipac.cpa.model.domain.PersonType;
import br.com.unipac.cpa.model.repository.ProfessorRepository;
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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@ImportAutoConfiguration(exclude = FlywayAutoConfiguration.class)
public class ProfessorRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProfessorRepository clientRepository;

    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void should_find_no_Clients_if_repository_is_empty() {
        Iterable<Professor> seeds = clientRepository.findAll();
        assertThat(seeds).isEmpty();
    }

    private Professor getClient() {
        return Professor.builder()
                .name("Bradesco")
                .email("root@localhost")
                .mobile("9999999999")
                .company(getCompany())
                .build();
    }

    private Company getCompany() {
        return Company.builder()
                .name("Everis")
                .email("root@localhost")
                .address("Joao naves de Avela")
                .personType(PersonType.LEGAL)
                .phone("9999999999")
                .mobile("9999999999")
                .documentRegion(DocumentRegion.STATE_MG)
                .socialId(9999999999l)
                .nationality("Brasilian")
                .build();
    }

    @Test
    public void should_store_a_Client() {
        Professor professor = getClient();
        Professor Professor = clientRepository.save(professor);

        assertThat(Professor).hasFieldOrPropertyWithValue("name","Everis");
        assertThat(Professor).hasFieldOrPropertyWithValue("mobile","9999999999");
        assertThat(Professor).hasFieldOrPropertyWithValue("company", getCompany());
    }

    @Test(expected = ConstraintViolationException.class)
    public void should_throw_constraint_violation_execption_Client_is_null() {
        thrown.expect(ConstraintViolationException.class);
        thrown.expectMessage("The field Professor is required");
        thrown.expectMessage("must not be null");
        Professor Professor = new Professor(null ,"root@localhost","3499999999",getCompany());

        entityManager.persistAndFlush(Professor);
    }

    @Test
    public void should_found_store_a_Client() {
        Professor professor = getClient();
        entityManager.persist(professor);

        Optional<Professor> found = clientRepository.findById(getClient().getId());
        assertThat(found.get()).isEqualTo(professor);
    }

    @Test
    public void should_found_null_Client() {
        Optional<Professor> fromDb = clientRepository.findById(1l);
        assertThat(fromDb.get()).isNull();
    }

    @Test
    public void whenFindById_thenReturnClient() {
        Professor professor = getClient();
        entityManager.persistAndFlush(professor);

        Professor fromDb = clientRepository.findById(professor.getId()).orElse(null);
        assertThat(fromDb).isEqualTo(professor);
    }

    @Test
    public void whenInvalidId_thenReturnNull() {
        Professor fromDb = clientRepository.findById(-11l).orElse(null);
        assertThat(fromDb).isNull();
    }

    @Test
    public void givenSetOfClients_whenFindAll_thenReturnAllClients() {
        Professor professor = getClient();
        Professor professor1 = getClient();
        Professor professor2 = getClient();

        entityManager.persist(professor);
        entityManager.persist(professor1);
        entityManager.persist(professor2);
        entityManager.flush();

        Iterator<Professor> allClients = clientRepository.findAll().iterator();
        List<Professor> professors = new ArrayList<>();
        allClients.forEachRemaining(c -> professors.add(c));

        assertThat(allClients).hasSize(3);//.extracting(Professor::getProfessor).containsOnly(professor.getProfessor(), Client2.getProfessor(), Client3.getProfessor());
    }
}
