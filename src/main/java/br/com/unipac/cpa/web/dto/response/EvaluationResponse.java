package br.com.unipac.cpa.web.dto.response;

import lombok.*;

import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Data public class EvaluationResponse {

	@Getter @Setter private Long id;
	@Getter @Setter private String name;
	@Getter @Setter private String description;
	@Getter @Setter private Date date;
}
