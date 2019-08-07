package br.com.unipac.cpa.repository;

import br.com.unipac.cpa.model.domain.Local;
import br.com.unipac.cpa.model.repository.LocalRepository;
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
public class LocalRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private LocalRepository localRepository;

    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void should_find_no_Locals_if_repository_is_empty() {
        Iterable<Local> seeds = localRepository.findAll();
        assertThat(seeds).isEmpty();
    }

    private Local getLocal() {
        return Local.builder()
                .city("Uberlandia")
                .uf("MG")
                .build();
    }

    @Test
    public void should_store_a_Local() {
        Local local = getLocal();
        Local Local = localRepository.save(local);

        assertThat(Local).hasFieldOrPropertyWithValue("city","Uberlandia");
        assertThat(Local).hasFieldOrPropertyWithValue("uf","MG");
    }

    @Test(expected = ConstraintViolationException.class)
    public void should_throw_constraint_violation_execption_Local_is_null() {
        thrown.expect(ConstraintViolationException.class);
        thrown.expectMessage("The field Local is required");
        thrown.expectMessage("must not be null");
        Local Local = new Local(null ,"MG");

        entityManager.persistAndFlush(Local);
    }

    @Test
    public void should_found_store_a_Local() {
        Local local = getLocal();
        entityManager.persist(local);

        Optional<Local> found = localRepository.findById(getLocal().getId());
        assertThat(found.get()).isEqualTo(local);
    }

    @Test
    public void should_found_null_Local() {
        Optional<Local> fromDb = localRepository.findById(1l);
        assertThat(fromDb.get()).isNull();
    }

    @Test
    public void whenFindById_thenReturnLocal() {
        Local local = getLocal();
        entityManager.persistAndFlush(local);

        Local fromDb = localRepository.findById(local.getId()).orElse(null);
        assertThat(fromDb).isEqualTo(local);
    }

    @Test
    public void whenInvalidId_thenReturnNull() {
        Local fromDb = localRepository.findById(-11l).orElse(null);
        assertThat(fromDb).isNull();
    }

    @Test
    public void givenSetOfLocals_whenFindAll_thenReturnAllLocals() {
        Local local = getLocal();
        Local local1 = getLocal();
        Local local2 = getLocal();

        entityManager.persist(local);
        entityManager.persist(local1);
        entityManager.persist(local2);
        entityManager.flush();

        Iterator<Local> allLocals = localRepository.findAll().iterator();
        List<Local> locals = new ArrayList<>();
        allLocals.forEachRemaining(c -> locals.add(c));

        assertThat(allLocals).hasSize(3);//.extracting(Local::getLocal).containsOnly(local.getLocal(), Local2.getLocal(), Local3.getLocal());
    }
}
