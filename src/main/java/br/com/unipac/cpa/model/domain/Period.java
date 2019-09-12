package br.com.unipac.cpa.model.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "period")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true, of = { "name", "description", "professor","course","disciplines" })
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data public class Period extends AudityEntity {

    private static final long serialVersionUID = -3713207158221953195L;

    @NotNull
    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "professor_id", referencedColumnName = "id")
    private Professor professor;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    private Course course;

    @Getter
    @Setter
    @OneToMany(mappedBy = "id")
    private List<Discipline> disciplines;



    public void update(Period period){
        this.name = period.getName();
        this.description = period.getDescription();
        this.professor = period.getProfessor();
        this.course = period.getCourse();
        this.disciplines = period.getDisciplines();
    }
}
