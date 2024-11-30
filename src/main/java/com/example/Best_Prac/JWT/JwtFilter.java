package com.example.Best_Prac.JWT;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.Best_Prac.Service.LoginService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import com.example.Best_Prac.JWT.Service.LoginService;
//
//import java.io.IOException;
//@Component
//public class JwtFilter extends OncePerRequestFilter {
//
//    @Autowired
//    private JwtUtil jwtService;
//
//    @Autowired
//    private LoginService loginService;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        String authHeader= request.getHeader("Authorization");
//        String token= null;
//        String username= null;
//
//        if(authHeader !=null && authHeader.startsWith("Bearer "))
//        {
//            token = authHeader.substring(7);
//            username=jwtService.extractUserName(token);
//        }
//        if(username!=null && SecurityContextHolder.getContext().getAuthentication() ==null)
//        {
//            UserDetails userDetails = this.loginService.loadUserByUsername(username);
//            if(jwtService.validateToken(token,userDetails))
//            {
//                UsernamePasswordAuthenticationToken authToken= new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
//
//                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                SecurityContextHolder.getContext().setAuthentication(authToken);
//            }
//        }
//        filterChain.doFilter(request,response);
//    }
//
//}



//------------------------------------------------------------------------------------------------------------------------------


//@Component
//public class JwtFilter extends OncePerRequestFilter {
//
//    @Autowired
//    private JwtUtil jwtUtil;
//
//    @Autowired
//    private LoginService loginService;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        final String authorizationHeader = request.getHeader("Authorization");
//
//        String username = null;
//        String jwt = null;
//
//        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
//            jwt = authorizationHeader.substring(7);
//            username = jwtUtil.getUsernameFromToken(jwt);
//        }
//
//        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//            UserDetails userDetails = this.loginService.loadUserByUsername(username);
//            if (jwtUtil.validateToken(jwt, userDetails)) {
//                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                SecurityContextHolder.getContext().setAuthentication(authToken);
//            }
//        }
//
//        filterChain.doFilter(request, response);
//    }
//}

@Component
public class JwtFilter extends OncePerRequestFilter{

	private JwtUtil jwtUtil;

	private LoginService loginService;

	public JwtFilter(JwtUtil jwtUtil, LoginService loginService) {
		this.jwtUtil = jwtUtil;
		this.loginService = loginService;
	}

	protected void doFilterInternal(HttpServletRequest req,HttpServletResponse resp,FilterChain filterChain)throws IOException,ServletException{
		String authHeader=req.getHeader("Authorization");
		String token=null;
		String username=null;
		if(authHeader!=null && authHeader.startsWith("Bearer "))
		{
			token=authHeader.substring(7);
			username=jwtUtil.getUsername(token);
		}
		if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null)
		{
			UserDetails user=this.loginService.loadUserByUsername(username);
			if(jwtUtil.validateToken(token,user))
			{
				UsernamePasswordAuthenticationToken auth=new UsernamePasswordAuthenticationToken(user, null,user.getAuthorities());
				auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
		}
		filterChain.doFilter(req, resp);
	}
}