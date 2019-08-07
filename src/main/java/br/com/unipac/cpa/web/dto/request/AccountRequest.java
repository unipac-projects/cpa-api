package br.com.unipac.cpa.web.dto.request;

import java.io.Serializable;
import java.util.List;

import br.com.unipac.cpa.web.dto.UserTypeDTO;
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
@Data
public class AccountRequest implements Serializable {
	private static final long serialVersionUID = -5623786798388768855L;
	
	@Getter @Setter private String email;
	@Getter @Setter private String password;
	@Getter @Setter private Long registerNumber;
	@Getter
	@Setter
	private String userType;
}
