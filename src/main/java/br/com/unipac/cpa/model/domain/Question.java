package br.com.unipac.cpa.model.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@Entity
@Table(name = "question")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true, of = {"title","description", "evaluation", "scala_likert_id"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Question extends AudityEntity {

    private static final long serialVersionUID = 225176755550681077L;

    @NotNull
    @Getter
    @Setter
    private String title;
    @Getter
    @Setter
    private String description;
    @Getter
    @Setter
    private String evaluation;
    @Getter
    @Setter
    @JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator.class, property = "@id", scope = Company.class)
    @ManyToOne
    @JoinColumn(name = "scala_likert_id")
    private Company scala_likert;

    public void  update(Question question){
        this.description = question.getDescription();
        this.evaluation = question.getEvaluation();
        this.scala_likert = question.getScala_likert();
    }

}
