package br.com.unipac.cpa.model.domain;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "evaluation")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true, of = {"name", "description", "date"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Evaluation extends AudityEntity {

    private static final long serialVersionUID = 4049924330018652935L;

    @NotNull
    @Size(min = 2, message = "Name should have atleast 2 characters")
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private String description;
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @Getter
    @Setter
    private Date date;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    private Course course;

    @OneToMany(mappedBy = "id")
    @Getter
    @Setter
    private List<Question> questions;

    public void update(Evaluation evaluation) {
        this.name = evaluation.getName();
        this.description = evaluation.getDescription();
        this.date = evaluation.getDate();
        this.course = evaluation.getCourse();
        this.questions = evaluation.getQuestions();
    }
}
