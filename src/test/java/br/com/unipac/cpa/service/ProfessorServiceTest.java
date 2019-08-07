package br.com.unipac.cpa.service;

import br.com.unipac.cpa.exception.URLException;
import br.com.unipac.cpa.model.domain.Professor;
import br.com.unipac.cpa.model.domain.Company;
import br.com.unipac.cpa.model.domain.DocumentRegion;
import br.com.unipac.cpa.model.domain.PersonType;
import br.com.unipac.cpa.model.repository.ProfessorRepository;
import br.com.unipac.cpa.model.service.impl.ProfessorServiceImpl;
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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ProfessorServiceTest {

    @InjectMocks
    ProfessorServiceImpl clientService;

    @Mock
    ProfessorRepository urlRepository;

    public ExpectedException thrown = ExpectedException.none();

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

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllEmployeesTest() {
        List<Professor> list = new ArrayList<>();

        Professor professor = getClient();
        Professor professor1 = getClient();
        Professor professor2 = getClient();

        list.add(professor);
        list.add(professor1);
        list.add(professor2);

        when(urlRepository.findAll()).thenReturn(list);

        // test
        List<Professor> urls = clientService.listAll();

        assertEquals(3, urls.size());
        verify(urlRepository, times(1)).findAll();
    }

    @Test
    public void getClientByIdTest() {
        when(urlRepository.findById(1L)).thenReturn(Optional.of(getClient()));

        Optional<Professor> client = clientService.findById(1L);

        assertEquals("Everis", client.get().getName());
        assertEquals("root@localhost", client.get().getEmail());
    }

    @Test
    public void getFindClientByShortIdTest() {
        Professor professor = getClient();
        when(clientService.findById(1l)).thenReturn(Optional.ofNullable(professor));

        Optional<Professor> result = clientService.findById(1l);

        assertEquals("Everis", professor.getName());
        assertEquals("root@localhost", professor.getEmail());
    }

    @Test
    public void createClientTest() {
        Professor url = getClient();
        clientService.save(url);

        verify(urlRepository, times(1)).save(url);
    }

    @Test
    public void createAndStoreClientTest() {
        Professor professor = getClient();
        clientService.save(professor);

        when(clientService.save(professor)).thenReturn(professor);
        Professor result = clientService.save(professor);

        assertEquals("root@localhost", result.getEmail());
    }

    @Test(expected = URLException.class)
    public void createAndStoreClientTest_and_should_throw_constraint_violation_execption() {
        thrown.expect(URLException.class);
        thrown.expectMessage("Professor is not valid!");

        Professor professor = getClient();
        clientService.save(professor);

        when(clientService.save(professor)).thenReturn(professor);
        Professor result = clientService.save(professor);

        assertEquals("root@localhost", result.getEmail());
    }

}
