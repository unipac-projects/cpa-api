package br.com.unipac.cpa.web.dto.request;

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
@ToString
@Data public class ProfessorRequest {

	@Getter @Setter private String name;
	@Getter @Setter private String email;
	@Getter @Setter private String mobile;
	@Getter @Setter private Long companyId;

}
