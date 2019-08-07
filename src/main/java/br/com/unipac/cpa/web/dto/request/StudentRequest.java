package br.com.unipac.cpa.web.dto.request;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Data public class StudentRequest {

	@Getter @Setter private String name;
	@Getter @Setter private String email;
	@Getter @Setter private String mobile;
	@Getter @Setter private int register;
	@Getter @Setter private Long companyId;

}
