package br.com.unipac.cpa.service;

import br.com.unipac.cpa.exception.URLException;
import br.com.unipac.cpa.model.domain.Company;
import br.com.unipac.cpa.model.domain.DocumentRegion;
import br.com.unipac.cpa.model.domain.PersonType;
import br.com.unipac.cpa.model.repository.CompanyRepository;
import br.com.unipac.cpa.model.service.impl.CompanyServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CompanyServiceTest {

    @InjectMocks
    CompanyServiceImpl companyService;

    @Mock
    CompanyRepository companyRepository;

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

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllEmployeesTest() {
        List<Company> list = new ArrayList<>();

        Company client = getCompany();
        Company client1 = getCompany();
        Company client2 = getCompany();

        list.add(client);
        list.add(client1);
        list.add(client2);

        when(companyRepository.findAll()).thenReturn(list);

        // test
        List<Company> urls = companyService.listAll();

        assertEquals(3, urls.size());
        verify(companyRepository, times(1)).findAll();
    }

    @Test
    public void getCompanyByIdTest() {
        when(companyRepository.findById(1L)).thenReturn(Optional.of(getCompany()));

        Optional<Company> client = companyService.findById(1L);

        assertEquals("Everis", client.get().getName());
        assertEquals("root@localhost", client.get().getEmail());
    }

    @Test
    public void getFindCompanyByShortIdTest() {
        Company client = getCompany();
        when(companyService.findById(1l)).thenReturn(Optional.ofNullable(client));

        Optional<Company> result = companyService.findById(1l);

        assertEquals("Everis", client.getName());
        assertEquals("root@localhost", client.getEmail());
    }

    @Test
    public void createCompanyTest() {
        Company url = getCompany();
        companyService.save(url);

        verify(companyRepository, times(1)).save(url);
    }

    @Test
    public void createAndStoreCompanyTest() {
        Company client = getCompany();
        companyService.save(client);

        when(companyService.save(client)).thenReturn(client);
        Company result = companyService.save(client);

        assertEquals("root@localhost", result.getEmail());
    }

    @Test(expected = URLException.class)
    public void createAndStoreCompanyTest_and_should_throw_constraint_violation_execption() {
        thrown.expect(URLException.class);
        thrown.expectMessage("Company is not valid!");

        Company client = getCompany();
        companyService.save(client);

        when(companyService.save(client)).thenReturn(client);
        Company result = companyService.save(client);

        assertEquals("root@localhost", result.getEmail());
    }

}
