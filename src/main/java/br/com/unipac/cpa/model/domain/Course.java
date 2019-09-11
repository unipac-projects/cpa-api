package br.com.unipac.cpa.model.domain;

import javax.persistence.*;

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

@Entity
@Table(name = "course")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true, of = { "name", "code" })
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Course extends AudityEntity {

	private static final long serialVersionUID = -7030639047749953619L;

	@Getter
	@Setter
	private String name;
	@JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator.class, property = "@id", scope = Company.class)
	@ManyToOne
	@JoinColumn(name = "company_id",referencedColumnName = "id")
	@Getter
	@Setter
	private Company company;

	@OneToMany
	@JoinColumn(name = "period_id",referencedColumnName = "id")
	@Getter
	@Setter
	private Period period;

	public void update(Course course) {
		this.name = course.getName();
		this.company = course.getCompany();
	}

}
