package br.com.unipac.cpa.model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "company")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true, of = { "name", "email", "address", "companyType", "personType", "phone",
		"mobile", "documentRegion", "socialId", "nationality" })
@Data
public class Company extends AudityEntity {
	private static final long serialVersionUID = 8401721385730134860L;

	@NotNull
	@Size(min = 2, message = "Name should have atleast 2 characters")
	@Getter
	@Setter
	private String name;
	@NotNull
	@Email
	@Getter
	@Setter
	private String email;
	
	@NotNull
	@Size(min = 11, message = "Address should have atleast 11 characters")
	@Getter
	@Setter
	private String address;
	
	@ManyToOne
	@JoinColumn(name = "company_type_id")
	@Getter
	@Setter
	private CompanyType companyType;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "person_type")
	@Getter
	@Setter
	private PersonType personType;
	
	@Getter
	@Setter
	private String phone;
	
	@NotNull
	@Getter
	@Setter
	private String mobile;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "document_region")
	@Getter
	@Setter
	private DocumentRegion documentRegion;
	
	@NotNull
	// @Size(min = 11, max = 11, message = "cpf should have atleast 11 characters")
	@Column(name = "social_id")
	@Getter
	@Setter
	private Long socialId;
	
	@Getter
	@Setter
	private String nationality;

	@ManyToOne
	@JoinColumn(name = "local_id")
	@Getter
	@Setter
	private Local local;


	public void updade(Long id, Company company) {
		super.setId(id);
		this.name = company.getName();
		this.email = company.getEmail();
		this.companyType = company.getCompanyType();
		this.address = company.getAddress();
		this.personType = company.getPersonType();
		this.documentRegion = company.getDocumentRegion();
		this.socialId = company.getSocialId();
		this.socialId = company.getSocialId();
		this.nationality = company.getNationality();
		this.local = company.getLocal();
	}
}
