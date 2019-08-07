package br.com.unipac.cpa.web.dto.response;

import br.com.unipac.cpa.model.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString(callSuper = true, of = { "id", "roleName", "user" })
@Data
public class UserRoleResponse {

	@Getter
	@Setter
	private Long id;

	@Getter
	@Setter
	private String roleName;

	@Getter
	@Setter
	private User user;

}
