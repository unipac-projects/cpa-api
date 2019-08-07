package br.com.unipac.cpa.web.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true, of = { "role" })
@Data
public class UserTypeDTO {

    @Getter
    @Setter
    private String role;

}
