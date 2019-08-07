package br.com.unipac.cpa.model.domain;

public enum Sex {
	MALE("M", "Masculino"), FEMALE("F", "Feminino"), OTHERS("@", "Outros");

	private String acronym;
	private String type;

	private Sex(String acronym, String type) {
		this.acronym = acronym;
		this.type = type;
	}

	public String getAcronym() {
		return acronym;
	}

	public String getType() {
		return type;
	}

	public String getSexById(String acronym) {
		for (Sex sex : Sex.values()) {
			if (sex.getAcronym().equals(acronym)) {
				return sex.getType();
			}
		}
		return null;
	}
}
