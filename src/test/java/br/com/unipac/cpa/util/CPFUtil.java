package br.com.unipac.cpa.util;

import br.com.caelum.stella.validation.CPFValidator;
import br.com.unipac.cpa.exception.NotImplementationConstructionException;

public final class CPFUtil {

	private CPFUtil() {
		throw new NotImplementationConstructionException("Essa classe nao pode ser instanciada");
	}

	public static boolean valida(Long documentId) {
		CPFValidator cpfValidator = new CPFValidator();
		try {
			cpfValidator.assertValid(String.valueOf(documentId));
			return Boolean.TRUE;
		} catch (Exception e) {
			e.printStackTrace();
			return Boolean.FALSE;
		}
	}
}
