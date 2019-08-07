package br.com.unipac.cpa.repository;

import br.com.unipac.cpa.model.domain.Company;
import br.com.unipac.cpa.model.domain.DocumentRegion;
import br.com.unipac.cpa.model.domain.PersonType;
import br.com.unipac.cpa.model.repository.CompanyRepository;
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
public class CompanyRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CompanyRepository companyRepository;

    public ExpectedException thrown = ExpectedException.none();

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
    public void should_find_no_companys_if_repository_is_empty() {
        Iterable<Company> seeds = companyRepository.findAll();
        assertThat(seeds).isEmpty();
    }

    @Test
    public void should_store_a_company() {
        Company company = companyRepository.save(getCompany());

        assertThat(company).hasFieldOrPropertyWithValue("name","Everis");
        assertThat(company).hasFieldOrPropertyWithValue("email","root@localhost");
        assertThat(company).hasFieldOrPropertyWithValue("address","Joao naves de Avela");
        assertThat(company).hasFieldOrPropertyWithValue("personType", PersonType.LEGAL);
        assertThat(company).hasFieldOrPropertyWithValue("phone","9999999999");
        assertThat(company).hasFieldOrPropertyWithValue("mobile","9999999999");
        assertThat(company).hasFieldOrPropertyWithValue("documentRegion", DocumentRegion.STATE_MG);
        assertThat(company).hasFieldOrPropertyWithValue("socialId",9999999999l);
        assertThat(company).hasFieldOrPropertyWithValue("nationality","Brasilian");
    }

    @Test(expected = ConstraintViolationException.class)
    public void should_throw_constraint_violation_execption_company_is_null() {
        thrown.expect(ConstraintViolationException.class);
        thrown.expectMessage("The field company is required");
        thrown.expectMessage("must not be null");
        Company company = getCompany();
        entityManager.persistAndFlush(company);
    }

    @Test
    public void should_found_store_a_company() {
        Company company = getCompany();
        entityManager.persist(company);

        Optional<Company> found = companyRepository.findById(company.getId());
        assertThat(found.get()).isEqualTo(company);
    }

    @Test
    public void should_found_null_company() {
        Optional<Company> fromDb = companyRepository.findById(10L);
        assertThat(fromDb.get()).isNull();
    }

    @Test
    public void whenFindById_thenReturnCompany() {
        Optional<Company> company = companyRepository.findById(1L);
        entityManager.persistAndFlush(company);

        Company fromDb = companyRepository.findById(company.get().getId()).orElse(null);
        assertThat(fromDb).isEqualTo(company.get());
    }

    @Test
    public void whenInvalidId_thenReturnNull() {
        Company fromDb = companyRepository.findById(-11l).orElse(null);
        assertThat(fromDb).isNull();
    }

    @Test
    public void givenSetOfCompanys_whenFindAll_thenReturnAllCompanys() {
        Company company1 = getCompany();
        Company company2 = getCompany();
        Company company3 = getCompany();

        entityManager.persist(company1);
        entityManager.persist(company2);
        entityManager.persist(company3);
        entityManager.flush();

        Iterator<Company> allCompanys = companyRepository.findAll().iterator();
        List<Company> clients = new ArrayList<>();
        allCompanys.forEachRemaining(c -> clients.add(c));

        assertThat(allCompanys).hasSize(3); //.extracting(Company::getCompany).containsOnly(company1.getCompany(), company2.getCompany(), company3.getCompany());
    }
}
