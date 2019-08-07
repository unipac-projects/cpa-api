package br.com.unipac.cpa.constants;

import br.com.unipac.cpa.exception.NotImplementationConstructionException;

public final class AuthoritiesConstants {
	
	public static final String ANONYMOUS = "ROLE_ANONYMOUS";
	public static final String ADMIN = "ROLE_ADMIN";
	public static final String USER = "ROLE_USER";

	private AuthoritiesConstants() {
		throw new NotImplementationConstructionException("Classe não pode ser instanciada");
	}

}
