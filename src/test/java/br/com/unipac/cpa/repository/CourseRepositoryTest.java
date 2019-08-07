package br.com.unipac.cpa.repository;

import br.com.unipac.cpa.model.domain.Course;
import br.com.unipac.cpa.model.domain.Company;
import br.com.unipac.cpa.model.domain.DocumentRegion;
import br.com.unipac.cpa.model.domain.PersonType;
import br.com.unipac.cpa.model.repository.CourseRepository;
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
public class CourseRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CourseRepository courseRepository;

    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void should_find_no_Segments_if_repository_is_empty() {
        Iterable<Course> seeds = courseRepository.findAll();
        assertThat(seeds).isEmpty();
    }

    private Course getSegment() {
        return Course.builder()
                .name("Bradesco")
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
    public void should_store_a_Segment() {
        Course course = getSegment();
        Course Course = courseRepository.save(course);

        assertThat(Course).hasFieldOrPropertyWithValue("name","Everis");
        assertThat(Course).hasFieldOrPropertyWithValue("mobile","9999999999");
        assertThat(Course).hasFieldOrPropertyWithValue("company", getCompany());
    }

    @Test(expected = ConstraintViolationException.class)
    public void should_throw_constraint_violation_execption_Segment_is_null() {
        thrown.expect(ConstraintViolationException.class);
        thrown.expectMessage("The field Course is required");
        thrown.expectMessage("must not be null");
        Course Course = new Course(null , getCompany());

        entityManager.persistAndFlush(Course);
    }

    @Test
    public void should_found_store_a_Segment() {
        Course course = getSegment();
        entityManager.persist(course);

        Optional<Course> found = courseRepository.findById(getSegment().getId());
        assertThat(found.get()).isEqualTo(course);
    }

    @Test
    public void should_found_null_Segment() {
        Optional<Course> fromDb = courseRepository.findById(1l);
        assertThat(fromDb.get()).isNull();
    }

    @Test
    public void whenFindById_thenReturnSegment() {
        Course course = getSegment();
        entityManager.persistAndFlush(course);

        Course fromDb = courseRepository.findById(course.getId()).orElse(null);
        assertThat(fromDb).isEqualTo(course);
    }

    @Test
    public void whenInvalidId_thenReturnNull() {
        Course fromDb = courseRepository.findById(-11l).orElse(null);
        assertThat(fromDb).isNull();
    }

    @Test
    public void givenSetOfSegments_whenFindAll_thenReturnAllSegments() {
        Course course = getSegment();
        Course course1 = getSegment();
        Course course2 = getSegment();

        entityManager.persist(course);
        entityManager.persist(course1);
        entityManager.persist(course2);
        entityManager.flush();

        Iterator<Course> allSegments = courseRepository.findAll().iterator();
        List<Course> courses = new ArrayList<>();
        allSegments.forEachRemaining(c -> courses.add(c));

        assertThat(allSegments).hasSize(3);//.extracting(Course::getSegment).containsOnly(course.getSegment(), Segment2.getSegment(), Segment3.getSegment());
    }
}
