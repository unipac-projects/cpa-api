package br.com.unipac.cpa.service;

import br.com.unipac.cpa.exception.URLException;
import br.com.unipac.cpa.model.domain.Company;
import br.com.unipac.cpa.model.domain.Course;
import br.com.unipac.cpa.model.domain.DocumentRegion;
import br.com.unipac.cpa.model.domain.PersonType;
import br.com.unipac.cpa.model.repository.CourseRepository;
import br.com.unipac.cpa.model.service.impl.CourseServiceImpl;
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
public class CourseServiceTest {

    @InjectMocks
    CourseServiceImpl companyTypeService;

    @Mock
    CourseRepository companyTypeRepository;

    public ExpectedException thrown = ExpectedException.none();

    private Course getSegment() {
        return Course.builder()
                .name("Everis")
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
        List<Course> list = new ArrayList<>();

        Course client = getSegment();
        Course client1 = getSegment();
        Course client2 = getSegment();

        list.add(client);
        list.add(client1);
        list.add(client2);

        when(companyTypeRepository.findAll()).thenReturn(list);

        // test
        List<Course> urls = companyTypeService.listAll();

        assertEquals(3, urls.size());
        verify(companyTypeRepository, times(1)).findAll();
    }

    @Test
    public void getSegmentByIdTest() {
        when(companyTypeRepository.findById(1L)).thenReturn(Optional.of(getSegment()));

        Optional<Course> client = companyTypeService.findById(1L);

        assertEquals("Everis", client.get().getName());
    }

    @Test
    public void getFindSegmentByShortIdTest() {
        Course client = getSegment();
        when(companyTypeService.findById(1l)).thenReturn(Optional.ofNullable(client));

        Optional<Course> result = companyTypeService.findById(1l);

        assertEquals("Everis", client.getName());
    }

    @Test
    public void createSegmentTest() {
        Course url = getSegment();
        companyTypeService.save(url);

        verify(companyTypeRepository, times(1)).save(url);
    }

    @Test
    public void createAndStoreSegmentTest() {
        Course client = getSegment();
        companyTypeService.save(client);

        when(companyTypeService.save(client)).thenReturn(client);
        Course result = companyTypeService.save(client);

       // assertEquals("root@localhost", result.getName());
    }

    @Test(expected = URLException.class)
    public void createAndStoreSegmentTest_and_should_throw_constraint_violation_execption() {
        thrown.expect(URLException.class);
        thrown.expectMessage("Course is not valid!");

        Course client = getSegment();
        companyTypeService.save(client);

        when(companyTypeService.save(client)).thenReturn(client);
        Course result = companyTypeService.save(client);

        //assertEquals("root@localhost", result.getName());
    }

}
