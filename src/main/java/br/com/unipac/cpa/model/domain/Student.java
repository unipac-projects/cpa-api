package br.com.unipac.cpa.model.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "student")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true, of = { "name", "email", "register", "mobile", "company" })
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data public class Student extends AudityEntity {

	private static final long serialVersionUID = 225176755550680077L;

	@NotNull
	@Getter
	@Setter
	private String name;
	@Getter
	@Setter
	private String email;
	@Getter
	@Setter
	private int register;
	@Getter
	@Setter
	private String mobile;
	@Getter
	@Setter
	@JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator.class, property = "@id", scope = Company.class)
	@ManyToOne
	@JoinColumn(name = "company_id")
	private Company company;

	@ManyToMany(mappedBy = "student_discipline")
	private List<Discipline>disciplines;

	public void update(Student student) {
		this.name = student.getName();
		this.email = student.getEmail();
		this.mobile = student.getMobile();
		this.register = student.getRegister();
		this.company = student.getCompany();
		this.disciplines = student.getDisciplines();
	}

}
