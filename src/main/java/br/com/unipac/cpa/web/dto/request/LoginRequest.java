package br.com.unipac.cpa.web.dto.request;

import lombok.*;

import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true, of = { "email", "password", "userType"})
public class LoginRequest implements Serializable {

    private static final long serialVersionUID = 4023504641313778939L;

    @Getter
    @Setter
    private String email;
    @Getter
    @Setter
    private String password;
}
