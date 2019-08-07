package br.com.unipac.cpa.web.dto.response;

import java.io.Serializable;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@ToString(callSuper = true, of = { "email", "password" })
@Data
public class UserResponse implements Serializable {

	private static final long serialVersionUID = 6280539273496622196L;

	@Getter
	@Setter
	private Long id;
	
	@Getter
	@Setter
	private String email;

	@Getter
	@Setter
	private String password;

}