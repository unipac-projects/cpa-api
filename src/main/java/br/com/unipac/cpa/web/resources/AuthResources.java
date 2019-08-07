package br.com.unipac.cpa.web.resources;

import javax.servlet.ServletException;

import br.com.unipac.cpa.web.dto.request.LoginRequest;
import br.com.unipac.cpa.web.support.AuthSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import br.com.unipac.cpa.model.dto.TokenResult;

@RestController
@RequestMapping("/v1/auth")
public class AuthResources {

	@Autowired
	private AuthSupport authSupport;

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public TokenResult login(@RequestBody LoginRequest loginRequest) throws ServletException {
		return authSupport.validate(loginRequest);
	}

}
