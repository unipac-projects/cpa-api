package br.com.unipac.cpa.model.domain;

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

@Entity
@Table(name = "professor")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true, of = { "name", "email", "mobile", "company" })
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data public class Professor extends AudityEntity {

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
	private String mobile;
	@Getter
	@Setter
	@JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator.class, property = "@id", scope = Company.class)
	@ManyToOne
	@JoinColumn(name = "company_id")
	private Company company;

	public void update(Professor professor) {
		this.name = professor.getName();
		this.email = professor.getEmail();
		this.mobile = professor.getMobile();
		this.company = professor.getCompany();
	}

}
