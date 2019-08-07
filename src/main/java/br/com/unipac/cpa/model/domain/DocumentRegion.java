package br.com.unipac.cpa.model.domain;

import java.util.ArrayList;
import java.util.List;

public enum DocumentRegion {
	STATE_AC("AC", "Acre"),
	STATE_AL("AL", "Alagoas"),
	STATE_AP("AP", "Amapá"),
	STATE_AM("AM", "Amazonas"),
	STATE_BA("BA", "Bahia"),
	STATE_CE("CE", "Ceará"),
	STATE_DF("DF", "Distrito Federal"),
	STATE_ES("ES", "Espírito Santo"),
	STATE_MA("MA", "Maranhão"),
	STATE_MT("MT", "Mato Grosso"),
	STATE_MS("MS", "Mato Grosso do Sul"),
	STATE_MG("MG", "Minas Gerais"),
	STATE_PA("PA", "Pará"),
	STATE_PB("PB", "Paraíba"),
	STATE_PE("PE", "Pernambuco"),
	STATE_PI("PI", "Piauí"),
	STATE_RJ("RJ", "Rio de Janeiro"),
	STATE_RN("RN", "Rio Grande do Norte"),
	STATE_RS("RS", "Rio Grande do Sul"),
	STATE_RO("RO", "Rondônia"),
	STATE_RR("RR", "Roraima"),
	STATE_SC("SC", "Santa Catarina"),
	STATE_SP("SP", "São Paulo"),
	STATE_SE("SE", "Sergipe"),
	STATE_TO("TO", "Tocantins");
	 
	private String acronym;
	private String state;
	
	private DocumentRegion(String acronym, String state) {
		this.acronym = acronym;
		this.state = state;
	}

	public String getAcronym() {
		return acronym;
	}

	public String getState() {
		return state;
	}
	
	public static DocumentRegion get(String states) {
		for (DocumentRegion region : DocumentRegion.values()) {
			if (states.equals(region.name())) {
				return region;
			}
		}
		return null;
	}
	
	public static String getStateByAcronym(String acronym) {
		for (DocumentRegion region : DocumentRegion.values()) {
			if (region.getAcronym().equals(acronym)) {
				return region.getState();
			}
		}
		return null;
	}
	
	public static List<DocumentRegion> getDocumentRegions() {
		List<DocumentRegion> regions = new ArrayList<>();
  		for (DocumentRegion region : DocumentRegion.values()) {
  			regions.add(region);
		}
		return regions;
	}
}
