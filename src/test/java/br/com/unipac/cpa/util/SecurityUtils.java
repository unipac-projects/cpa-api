package br.com.unipac.cpa.util;

import java.util.Collection;
import java.util.Collections;

import br.com.unipac.cpa.constants.AuthoritiesConstants;
import br.com.unipac.cpa.exception.NotImplementationConstructionException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Utility class for Spring Security.
 */
public class SecurityUtils {

	public static final String SYSTEM_EMAIL = "sistema@crestaurant.com.br";
	public static final String SYSTEM_NAME = "crestaurant";
	public static final Long SYSTEM_ID = 1l;

	private SecurityUtils() {
		throw new NotImplementationConstructionException("Classe não pode ser instanciada");
	}

	public static boolean isCurrentUserAdmin() {
		String currentLogin = getCurrentLogin();
		return SYSTEM_EMAIL.equals(currentLogin);
	}

	/**
	 * Get the login of the current user.
	 */
	public static String getCurrentLogin() {
		Authentication authentication = getAuthentication();
		UserDetails springSecurityUser;
		String userName = null;

		if (authentication != null) {
			if (authentication.getPrincipal() instanceof UserDetails) {
				springSecurityUser = (UserDetails) authentication.getPrincipal();
				userName = springSecurityUser.getUsername();
			} else if (authentication.getPrincipal() instanceof String) {
				userName = (String) authentication.getPrincipal();
			}
		}

		return userName;
	}

	public static Authentication getAuthentication() {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		return securityContext.getAuthentication();
	}

	/**
	 * Check if a user is authenticated.
	 *
	 * @return true if the user is authenticated, false otherwise
	 */
	public static boolean isAuthenticated() {
		SecurityContext securityContext = SecurityContextHolder.getContext();

		final Collection<? extends GrantedAuthority> authorities = securityContext.getAuthentication().getAuthorities();

		if (authorities != null) {
			for (GrantedAuthority authority : authorities) {
				if (authority.getAuthority().equals(AuthoritiesConstants.ANONYMOUS)) {
					return false;
				}
			}
		}

		return true;
	}

	public static void authenticateAsAdmin() {
		authenticateAs("ROLE_ADMIN", SecurityUtils.SYSTEM_EMAIL, "admin");
	}

	public static void authenticateAs(String role, String email, String password) {
		SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(role);
		Collection<? extends GrantedAuthority> authorities = Collections.singletonList(simpleGrantedAuthority);
		Authentication authentication = new UsernamePasswordAuthenticationToken(email, password, authorities);

		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	public static void logoff() {
		SecurityContextHolder.clearContext();
	}

}
