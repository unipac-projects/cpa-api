package br.com.unipac.cpa.web.dto.response;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Data
public class QuestionResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    private Long id;

    @NotNull
    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private String evaluationId;
}

