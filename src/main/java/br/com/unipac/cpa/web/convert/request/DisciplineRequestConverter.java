package br.com.unipac.cpa.web.convert.request;

import br.com.unipac.cpa.model.domain.Discipline;
import br.com.unipac.cpa.model.domain.Professor;
import br.com.unipac.cpa.model.repository.ProfessorRepository;
import br.com.unipac.cpa.web.dto.request.DisciplineRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DisciplineRequestConverter implements Converter<DisciplineRequest, Discipline> {

    @Autowired
    private ProfessorRepository clientRepository;

    @Override
    public Discipline convert(DisciplineRequest disciplineRequest) {
        Discipline discipline =  new Discipline();
        discipline.setName(disciplineRequest.getName());
        discipline.setDescription(disciplineRequest.getDescription());
        Optional<Professor> client = clientRepository.findById(disciplineRequest.getClientId());
        discipline.setProfessor(client.get());
        return discipline;
    }
}
