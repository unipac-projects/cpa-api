package br.com.unipac.cpa.web.dto.request;

import com.sun.istack.internal.NotNull;
import lombok.*;

import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Data

public class QuestionRequest implements Serializable {
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
