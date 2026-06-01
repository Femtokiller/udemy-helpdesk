package com.udemy.helpdesk.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.udemy.helpdesk.domain.Pessoa;
import com.udemy.helpdesk.repositories.PessoaRepository;
import com.udemy.helpdesk.security.UserSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		List<Pessoa> userList = pessoaRepository.findByEmail(email);
		
		if(!userList.isEmpty()) 
		{
			Pessoa user = userList.get(0);
			return new UserSS(user.getId(), user.getEmail(), user.getSenha(), user.getPerfils());			
		}
		
		throw new UsernameNotFoundException(email);		
	}

}
