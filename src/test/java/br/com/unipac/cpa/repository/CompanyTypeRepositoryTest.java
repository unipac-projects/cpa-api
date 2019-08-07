package br.com.unipac.cpa.repository;

import br.com.unipac.cpa.model.domain.CompanyType;
import br.com.unipac.cpa.model.repository.CompanyTypeRepository;
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
public class CompanyTypeRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CompanyTypeRepository companyTypeRepository;

    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void should_find_no_CompanyTypes_if_repository_is_empty() {
        Iterable<CompanyType> seeds = companyTypeRepository.findAll();
        assertThat(seeds).isEmpty();
    }

    private CompanyType getCompanyType() {
        return CompanyType.builder()
                .name("Bradesco")
                .build();
    }

    @Test
    public void should_store_a_CompanyType() {
        CompanyType companyType = getCompanyType();
        CompanyType CompanyType = companyTypeRepository.save(companyType);

        assertThat(CompanyType).hasFieldOrPropertyWithValue("name","Everis");
    }

    @Test(expected = ConstraintViolationException.class)
    public void should_throw_constraint_violation_execption_CompanyType_is_null() {
        thrown.expect(ConstraintViolationException.class);
        thrown.expectMessage("The field CompanyType is required");
        thrown.expectMessage("must not be null");
        CompanyType CompanyType = new CompanyType(null);

        entityManager.persistAndFlush(CompanyType);
    }

    @Test
    public void should_found_store_a_CompanyType() {
        CompanyType companyType = getCompanyType();
        entityManager.persist(companyType);

        Optional<CompanyType> found = companyTypeRepository.findById(getCompanyType().getId());
        assertThat(found.get()).isEqualTo(companyType);
    }

    @Test
    public void should_found_null_CompanyType() {
        Optional<CompanyType> fromDb = companyTypeRepository.findById(1l);
        assertThat(fromDb.get()).isNull();
    }

    @Test
    public void whenFindById_thenReturnCompanyType() {
        CompanyType companyType = getCompanyType();
        entityManager.persistAndFlush(companyType);

        CompanyType fromDb = companyTypeRepository.findById(companyType.getId()).orElse(null);
        assertThat(fromDb).isEqualTo(companyType);
    }

    @Test
    public void whenInvalidId_thenReturnNull() {
        CompanyType fromDb = companyTypeRepository.findById(-11l).orElse(null);
        assertThat(fromDb).isNull();
    }

    @Test
    public void givenSetOfCompanyTypes_whenFindAll_thenReturnAllCompanyTypes() {
        CompanyType companyType = getCompanyType();
        CompanyType companyType1 = getCompanyType();
        CompanyType companyType2 = getCompanyType();

        entityManager.persist(companyType);
        entityManager.persist(companyType1);
        entityManager.persist(companyType2);
        entityManager.flush();

        Iterator<CompanyType> allCompanyTypes = companyTypeRepository.findAll().iterator();
        List<CompanyType> companyTypes = new ArrayList<>();
        allCompanyTypes.forEachRemaining(c -> companyTypes.add(c));

        assertThat(allCompanyTypes).hasSize(3);//.extracting(CompanyType::getCompanyType).containsOnly(companyType.getCompanyType(), CompanyType2.getCompanyType(), CompanyType3.getCompanyType());
    }
}
