package br.com.unipac.cpa.web.dto.response;

import java.io.Serializable;

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
@ToString(callSuper = true, of = { "name" })
@Data
public class CompanyTypeResponse implements Serializable {
	private static final long serialVersionUID = -977335928847231066L;

	@Getter
	@Setter
	private Long id;

	@Getter
	@Setter
	private String name;

}
