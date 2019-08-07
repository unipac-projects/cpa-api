package br.com.unipac.cpa.model.service;

import br.com.unipac.cpa.model.domain.Student;

import java.util.Optional;

public interface StudentService extends CrudService<Student, Long> {

	boolean sendInformation(Student student);

	Optional<Student> findByName(String name);
}
