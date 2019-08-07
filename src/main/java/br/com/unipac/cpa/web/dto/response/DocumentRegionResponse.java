package br.com.unipac.cpa.web.dto.response;

import java.io.Serializable;

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
@ToString(callSuper = true, of = { "name", "sigla", "code" })
@Data
public class DocumentRegionResponse implements Serializable {
	private static final long serialVersionUID = 5661601145709869372L;
	
	@Getter
	@Setter
	private String name;
	
	@Getter
	@Setter
	private String sigla;
	
	@Getter
	@Setter
	private String code;

}
