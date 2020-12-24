package com.LH.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.getUserByUsername(username);
		if(user == null) {
			throw new UsernameNotFoundException("Không tìm thấy user");
		}
		return new MyUserDetails(user);
	}

	public boolean Dangky (User user)
	{
		User userdk= userRepository.save(user);
		return true;
	}
	
}
