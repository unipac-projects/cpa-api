package br.com.unipac.cpa.model.service;

import br.com.unipac.cpa.model.domain.User;

public interface CustomUserDetailsService {

	User loadCurrentUser();

}
