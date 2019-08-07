package br.com.unipac.cpa.model.service.impl;

import static java.util.Collections.emptyList;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.unipac.cpa.util.JWTUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenAuthenticationService {

	private TokenAuthenticationService() {
		throw new IllegalArgumentException("Classe não pode ser instânciada - TokenAuthenticationService ");
	}

	public static void addAuthentication(HttpServletResponse res, String username) {
		String JWT = Jwts.builder().setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + JWTUtil.EXPIRATIONTIME))
				.signWith(SignatureAlgorithm.HS512, JWTUtil.SECRET).compact();
		res.addHeader(JWTUtil.HEADER_STRING, JWTUtil.TOKEN_PREFIX + " " + JWT);
	}

	public static Authentication getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(JWTUtil.HEADER_STRING);
		if (token != null) {
			// parse the token.
			String user = Jwts.parser().setSigningKey(JWTUtil.SECRET)
					.parseClaimsJws(token.replace(JWTUtil.TOKEN_PREFIX, "")).getBody().getSubject();

			if (user != null)
				return new UsernamePasswordAuthenticationToken(user, null, emptyList());
			else
				return null;
		}
		return null;
	}
}

