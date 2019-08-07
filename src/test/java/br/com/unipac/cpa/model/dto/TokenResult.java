package br.com.unipac.cpa.model.dto;

import br.com.unipac.cpa.model.domain.Role;
import lombok.*;

import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString(callSuper = true, of = { "userId", "token", "roles" })
@Data
public class TokenResult {
	private Long userId;
	private String token;
	private Set<Role> roles;
}
