package br.com.unipac.cpa.web.dto.response;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Data public class StudentResponse {

	@Getter @Setter private Long id;
	@Getter @Setter private String name;
	@Getter @Setter private String email;
	@Getter @Setter private int register;
	@Getter @Setter private String mobile;
	@Getter @Setter private Long companyId;
}
