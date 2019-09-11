package br.com.unipac.cpa.model.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "period")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true, of = { "name", "description", "professor" })
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
    @JoinColumn(name = "client_id")
    private Professor professor;



    public void update(Period period){
        this.name = period.getName();
        this.description = period.getDescription();
        this.professor = period.getProfessor();
    }
}
