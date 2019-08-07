package br.com.unipac.cpa.model.domain;

import java.util.ArrayList;
import java.util.List;

public enum PersonType {
	LEGAL(1, "Pessoa Juridica"), PHYSICAL(2, "Pessoa Fisica");

	private int ordinal;
	private String name;

	private PersonType(int ordinal, String name) {
		this.ordinal = ordinal;
		this.name = name;
	}

	public int getOrdinal() {
		return ordinal;
	}

	public String getName() {
		return name;
	}

	public String getNameById(int id) {
		for (PersonType personType : PersonType.values()) {
			if (personType.getOrdinal() == id) {
				return personType.getName();
			}
		}
		return null;
	}
	
	public int getByType(String type) {
		for (PersonType personType : PersonType.values()) {
			if (type.equals(personType.name)) {
				return personType.getOrdinal();
			}
		}
		return 0;
	}
	
	public static List<PersonType> getPersonTypes() {
		List<PersonType> types = new ArrayList<>();
  		for (PersonType type : PersonType.values()) {
  			types.add(type);
		}
		return types;
	}
	
	public static PersonType get(String personType) {
		for (PersonType type : PersonType.values()) {
			if (personType.equals(type.name())) {
				return type;
			}
		}
		return null;
	}
}
