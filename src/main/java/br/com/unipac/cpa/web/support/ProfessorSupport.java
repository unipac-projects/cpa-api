package br.com.unipac.cpa.web.support;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.com.unipac.cpa.model.domain.Professor;
import br.com.unipac.cpa.model.service.ProfessorService;
import br.com.unipac.cpa.web.dto.request.ProfessorRequest;
import br.com.unipac.cpa.web.dto.response.ProfessorResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

@Component
public class ProfessorSupport {

	private final Logger log = LogManager.getLogger(this.getClass());

	@Autowired
	private ProfessorService studentService;

	@Autowired
	private ConversionService conversionService;
	
	public ProfessorResponse convertToFindById(Long id) {
		Optional<Professor> student = studentService.findById(id);
		ProfessorResponse founded = conversionService.convert(student.get(), ProfessorResponse.class);
		log.info("Professor" + founded.toString());
		return founded;
	}

	public ProfessorResponse convertToFindByName(String name) {
		Optional<Professor> student = studentService.findByName(name);
		ProfessorResponse founded = conversionService.convert(student.get(), ProfessorResponse.class);
		log.info("Professor: " + founded.toString());
		return founded;
	}

	public List<ProfessorResponse> list() {
		List<ProfessorResponse> studentes = new ArrayList<>();
		studentService.listAll().forEach(student -> {
			ProfessorResponse saved = conversionService.convert(student, ProfessorResponse.class);
			studentes.add(saved);
		});
		return studentes;
	}

	public ProfessorResponse convertToCreate(ProfessorRequest professorRequest) {
		Professor professor = conversionService.convert(professorRequest, Professor.class);
		Professor saved = studentService.save(professor);
		return getConverter(saved);
	}

	private ProfessorResponse getConverter(Professor professor) {
		return conversionService.convert(professor, ProfessorResponse.class);
	}

	public ProfessorResponse convertToChange(Long id, ProfessorRequest professorRequest) {
		Professor professor = conversionService.convert(professorRequest, Professor.class);
		Professor updated = studentService.edit(id, professor);
		return getConverter(updated);
	}

	public boolean remove(Long id) {
		return studentService.remove(id);
	}
}
