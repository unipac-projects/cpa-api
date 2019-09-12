package br.com.unipac.cpa.model.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "discipline")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true, of = { "name", "description", "professor" })
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data public class Discipline extends AudityEntity {

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
    @JoinColumn(name = "client_id")
    private Professor professor;

    @ManyToOne
    @JoinColumn(name = "period_id",referencedColumnName = "id")
    @Getter
    @Setter
    private Period period;

    public void update(Discipline discipline){
        this.name = discipline.getName();
        this.description = discipline.getDescription();
        this.professor = discipline.getProfessor();
        this.period = discipline.getPeriod();
    }
}
