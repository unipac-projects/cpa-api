package br.com.unipac.cpa.constants;

import br.com.unipac.cpa.exception.NotImplementationConstructionException;

public class Constants {

	// Auxs Constants for Controllers
	public static final String RESPONSE_UNSUCCESS = "unsuccess";

	public static final String RESPONSE_SUCCESS = "success";

	public static final String CURRENT_USER = "root@localhost";
	
	public static final String DADOS_DELETADOS = "Dados Deletados!";

	public static final String CLIENTS_IN_CACHE = "clientsInCache";

	public static final String COMPANYS_IN_CACHE = "companysInCache";

	public static final String COMPANYS_TYPES_IN_CACHE = "companysTypesInCache";

	public static final String SEGMENTS_IN_CACHE = "segmentsInCache";

	public static final String TOTAL = "Total: ";

	private Constants() {
		throw new NotImplementationConstructionException("Classe não pode ser instanciada");
	}

}
