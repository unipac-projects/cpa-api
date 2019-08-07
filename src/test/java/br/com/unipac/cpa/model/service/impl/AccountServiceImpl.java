package br.com.unipac.cpa.model.service.impl;

import br.com.unipac.cpa.model.domain.User;
import br.com.unipac.cpa.model.repository.UserRepository;
import br.com.unipac.cpa.model.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserRepository userRepository;



	@Override
	public boolean register(User user) {
		User result = userRepository.save(user);
		return result != null;
	}

}
