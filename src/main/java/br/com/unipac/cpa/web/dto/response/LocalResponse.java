package br.com.unipac.cpa.web.dto.response;

import lombok.*;

@ToString(callSuper = true, of = { "id", "address" })
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LocalResponse {

    @Getter
    @Setter private Long id;
    @Getter @Setter private String address;
}
