package br.com.unipac.cpa.web.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString(callSuper = true, of = { "name", "code"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseRequest {

	@Getter @Setter private String name;
	@Getter @Setter private Long companyId;
}
