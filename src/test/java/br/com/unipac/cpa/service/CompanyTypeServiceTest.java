package br.com.unipac.cpa.service;

import br.com.unipac.cpa.exception.URLException;
import br.com.unipac.cpa.model.domain.CompanyType;
import br.com.unipac.cpa.model.repository.CompanyTypeRepository;
import br.com.unipac.cpa.model.service.impl.CompanyTypeServiceImpl;
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
public class CompanyTypeServiceTest {

    @InjectMocks
    CompanyTypeServiceImpl companyTypeService;

    @Mock
    CompanyTypeRepository companyTypeRepository;

    public ExpectedException thrown = ExpectedException.none();

    private CompanyType getCompanyType() {
        return CompanyType.builder()
                .name("Everis")
                .build();
    }

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllEmployeesTest() {
        List<CompanyType> list = new ArrayList<>();

        CompanyType client = getCompanyType();
        CompanyType client1 = getCompanyType();
        CompanyType client2 = getCompanyType();

        list.add(client);
        list.add(client1);
        list.add(client2);

        when(companyTypeRepository.findAll()).thenReturn(list);

        // test
        List<CompanyType> urls = companyTypeService.listAll();

        assertEquals(3, urls.size());
        verify(companyTypeRepository, times(1)).findAll();
    }

    @Test
    public void getCompanyTypeByIdTest() {
        when(companyTypeRepository.findById(1L)).thenReturn(Optional.of(getCompanyType()));

        Optional<CompanyType> client = companyTypeService.findById(1L);

        assertEquals("Everis", client.get().getName());
    }

    @Test
    public void getFindCompanyTypeByShortIdTest() {
        CompanyType client = getCompanyType();
        when(companyTypeService.findById(1l)).thenReturn(Optional.ofNullable(client));

        Optional<CompanyType> result = companyTypeService.findById(1l);

        assertEquals("Everis", client.getName());
    }

    @Test
    public void createCompanyTypeTest() {
        CompanyType url = getCompanyType();
        companyTypeService.save(url);

        verify(companyTypeRepository, times(1)).save(url);
    }

    @Test
    public void createAndStoreCompanyTypeTest() {
        CompanyType client = getCompanyType();
        companyTypeService.save(client);

        when(companyTypeService.save(client)).thenReturn(client);
        CompanyType result = companyTypeService.save(client);

        assertEquals("root@localhost", result.getName());
    }

    @Test(expected = URLException.class)
    public void createAndStoreCompanyTypeTest_and_should_throw_constraint_violation_execption() {
        thrown.expect(URLException.class);
        thrown.expectMessage("CompanyType is not valid!");

        CompanyType client = getCompanyType();
        companyTypeService.save(client);

        when(companyTypeService.save(client)).thenReturn(client);
        CompanyType result = companyTypeService.save(client);

        assertEquals("root@localhost", result.getName());
    }

}
