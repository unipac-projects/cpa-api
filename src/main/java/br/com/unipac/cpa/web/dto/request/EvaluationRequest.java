package br.com.unipac.cpa.web.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Data public class EvaluationRequest {

	@Getter @Setter private String name;
	@Getter @Setter private String description;
	//@DateTimeFormat(pattern="dd/MM/yyyy HH:mm:ss")
	//@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Getter @Setter private String date;

}
