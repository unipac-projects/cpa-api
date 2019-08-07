package br.com.unipac.cpa.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Data public class ProfessorResponse implements Serializable {

	@Getter @Setter private Long id;
	@Getter @Setter private String name;
	@Getter @Setter private String email;
	@Getter @Setter private String mobile;
	@Getter @Setter private Long companyId;
}
