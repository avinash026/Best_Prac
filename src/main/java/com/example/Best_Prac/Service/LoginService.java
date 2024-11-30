package com.example.Best_Prac.Service;

import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.Best_Prac.Entity.UserModel;
import com.example.Best_Prac.Repo.UserRepository;

//import java.util.Collections;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import com.example.Best_Prac.Entity.User;
//import com.example.Best_Prac.Repo.UserRepository;
//
//@Service
//public class LoginService implements UserDetailsService{
//
//	@Autowired
//	private UserRepository userRepository;
//	
//	@Override
//	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//		// TODO Auto-generated method stub
//		User user=userRepository.findByEmail(email);
//		if(user==null)
//		{
//			throw new UsernameNotFoundException("User not found with email:"+email);
//		}
//		return buildUserForAuthentication(user);
//	}
//	private UserDetails buildUserForAuthentication(User user)
//	{
//		List<GrantedAuthority> authorities=Collections.singletonList(new SimpleGrantedAuthority(user.getRole().getRolename()));
//		return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),authorities);
//	}
//	
//    
//}


//------------------------------------------------------------------------------------------------------------------------------------
@Service
public class LoginService implements UserDetailsService
{
   private UserRepository userRepo;

	public LoginService(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserModel user=userRepo.findByEmail(email);
		if(user==null)
		{
			throw new UsernameNotFoundException("");
		}
		else
		{
			return buildUserForAuthentication(user);
		}
	}
	private UserDetails buildUserForAuthentication(UserModel user)
	{
		List<GrantedAuthority> authorities=Collections.singletonList(new SimpleGrantedAuthority(user.getRole().getRolename()));
		return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),authorities);
	}
	
}

     