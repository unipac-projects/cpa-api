package br.com.unipac.cpa.web.dto.request;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

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
@ToString(callSuper = true, of = { "name", "email", "address", "companyTypeId", "personType", "phone",
		"mobile", "documentRegion", "socialId", "nationality" })
@Data
public class CompanyRequest implements Serializable {
	private static final long serialVersionUID = 3406784206187867167L;

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
	private String personType;

	@Getter
	@Setter
	private String phone;

	@NotNull
	@Getter
	@Setter
	private String mobile;

	@Getter
	@Setter
	private String documentRegion;

	@Getter
	@Setter
	private Long socialId;

	@Getter
	@Setter
	private String nationality;

	@Getter
	@Setter
	private Long localId;
}
