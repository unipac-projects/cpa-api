package br.com.unipac.cpa.model.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import br.com.unipac.cpa.util.SecurityUtils;
import br.com.unipac.cpa.model.domain.Role;
import br.com.unipac.cpa.model.domain.User;
import br.com.unipac.cpa.model.repository.UserRepository;
import br.com.unipac.cpa.model.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@Qualifier("customUserDetailsService")
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService, UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(final String email) {
		List<GrantedAuthority> authorities = null;
		User user = userRepository.findByEmail(email);

		if (user != null) {
			Set<Role> roles = user.getRoles();
			authorities = buildUserAuthority(roles);
		}
		
		return buildUserForAuthentication(user, authorities);
	}

	@Transactional
	@Override
	public User loadCurrentUser() {
		return userRepository.findByEmail(SecurityUtils.getCurrentLogin());
	}

	private UserDetails buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), true,
				true, true, true, authorities);
	}

	private List<GrantedAuthority> buildUserAuthority(Set<Role> roles) {
		Set<GrantedAuthority> setAuths = new HashSet<>();

		// Build user's authorities
		for (Role role : roles) {
			setAuths.add(new SimpleGrantedAuthority(role.getRole()));
		}

		return new ArrayList<>(setAuths);
	}

}
