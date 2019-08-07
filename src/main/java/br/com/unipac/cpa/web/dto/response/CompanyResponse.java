package br.com.unipac.cpa.web.dto.response;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import br.com.unipac.cpa.model.domain.DocumentRegion;
import br.com.unipac.cpa.model.domain.PersonType;
import br.com.unipac.cpa.model.domain.Sex;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString(callSuper = true, of = { "name", "email", "address", "companyTypeId", "birthDate", "personType", "phone",
		"mobile", "documentRegion", "documentId", "socialId", "sex", "nationality" })
@Data
public class CompanyResponse implements Serializable {
	private static final long serialVersionUID = 3406784206187867167L;

	@Getter
	@Setter
	private Long id;

	@Getter
	@Setter
	private String name;

	@Getter
	@Setter
	private String email;

	@Getter
	@Setter
	private String address;

	@Getter
	@Setter
	private Long companyTypeId;

	@Getter
	@Setter
	private LocalDate birthDate;

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

	@Getter
	@Setter
	private DocumentRegion documentRegion;

	@Getter
	@Setter
	private Long documentId;

	@Getter
	@Setter
	private Long socialId;

	@Getter
	@Setter
	private Long localId;

	@Getter
	@Setter
	private Sex sex;

	@Getter
	@Setter
	private String nationality;
}
