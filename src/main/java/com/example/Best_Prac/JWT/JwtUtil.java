package com.example.Best_Prac.JWT;

import java.nio.charset.StandardCharsets;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.io.Decoders;
//import io.jsonwebtoken.security.Keys;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
//
//import com.example.Best_Prac.JWT.Service.UserService;
//
//import javax.crypto.KeyGenerator;
//import javax.crypto.SecretKey;
//import java.security.Key;
//import java.security.NoSuchAlgorithmException;
//import java.util.Base64;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.function.Function;
//@Component
//public class JwtUtil {
//    private final String secretKey = "randomKey123";
//    private static final long JWT_TOKEN_VALIDITY=5*60*60;
//    private static final long serialVersionUID=654352132132L;
////    public JwtUtil()
////    {
////        try {
////            KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
////            SecretKey sk=keyGen.generateKey();
////            secretKey=Base64.getEncoder().encodeToString(sk.getEncoded());
////        } catch (NoSuchAlgorithmException e) {
////            throw new RuntimeException(e);
////        }
////    }
//      @Autowired
//      private UserService userService;
//      
//    public String generatrToken(Map<String,Object> claims,String subject) {
//        return Jwts.builder()
//                .claims()
//                .add(claims)
//                .subject(subject)
//                .issuedAt(new Date(System.currentTimeMillis()))
//                .expiration(new Date(System.currentTimeMillis()+JWT_TOKEN_VALIDITY*1000 ))
//                .and()
//                .signWith(getKey())
//                .compact();
//
//    }
//
//    private SecretKey getKey() {
//        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
//        return Keys.hmacShaKeyFor(keyBytes);
//    }
//
//    public String extractUserName(String token) {
//        //extract the username from jwt token
//        return extractClaim(token, Claims::getSubject);
//
//    }
//    private <T> T extractClaim(String token, Function<Claims, T> claimResolver)
//    {
//        final Claims claims = extractAllClaims(token);
//        return claimResolver.apply(claims);
//    }
//
//    private Claims extractAllClaims(String token)
//    {
//        return Jwts.parser()
//                .verifyWith(getKey())
//                .build()
//                .parseSignedClaims(token)
//                .getPayload();
//    }
//    public boolean validateToken(String token, UserDetails userDetails) {
//        final String userName= extractUserName(token);
//        return(userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
//    }
//    private boolean isTokenExpired(String token)
//    {
//        return extractExpiration(token).before(new Date());
//
//    }
//    private Date extractExpiration(String token)
//    {
//        return extractClaim(token, Claims::getExpiration);
//    }
//
//}

//@Component
//public class JwtUtil {
//	private final String secretKey = "randomKey123";
//	private static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
//	private static final long serialVersionUID = 654352132132L;
//	public String getUsernameFromToken(String token)
//	{
//		return getClaimFromToken(token, Claims::getSubject);
//	}
//	public <T> T getClaimFromToken(String token,Function<Claims, T> claimResolver)
//	{
//		final Claims claims=getAllClaimsFromToken(token);
//		return claimResolver.apply(claims);
//	}

//	private Claims getAllClaimsFromToken(String token)
//	{
//		return Jwts.parser().verifyWith(getKey()).build().parseSignedClaims(token).getPayload();
//	}

//    private SecretKey getKey() {
//      byte[] keyBytes = Decoders.BASE64.decode(secretKey);
//      return Keys.hmacShaKeyFor(keyBytes);
//    }

//    public boolean validateToken(String token,UserDetails userDetails)
//    {
//    	final String username=getUsernameFromToken(token);
//    	return(username.equals(userDetails.getUsername()) && !isTokenExpired(token));
//    }
//    private boolean isTokenExpired(String token)
//    {
//    	return extractExpiration(token).before(new Date());
//    }
//    private Date extractExpiration(String token)
//    {
//    	return getClaimFromToken(token, Claims::getExpiration);
//    }
//}

//------------------------------------------------------------------------------------------------------------

//@Component
//public class JwtUtil {
//
//	private static final long serialVersionUID = 654352132132L;
//	public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60; // 5 hours
//
//	private final String secretKey = "randomkey123";
//
//	@Autowired
//	private UserService userService;
//
//	public String getUsernameFromToken(String token) {
//		return getClaimFromToken(token, Claims::getSubject);
//	}
//
//	public Date getExpirationDateFromToken(String token) {
//		return getClaimFromToken(token, Claims::getExpiration);
//	}
//
//	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
//		final Claims claims = getAllClaimsFromToken(token);
//		return claimsResolver.apply(claims);
//	}
//
//	private Claims getAllClaimsFromToken(String token) {
//		// return
//		// Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
//		return Jwts.parser().verifyWith(getKey()).build().parseSignedClaims(token).getPayload();
//	}
//
//	private SecretKey getKey() {
//		byte[] arr = Decoders.BASE64.decode(secretKey);
//		return Keys.hmacShaKeyFor(arr);
//	}
//
//	private Boolean isTokenExpired(String token) {
//		final Date expiration = getExpirationDateFromToken(token);
//		return expiration.before(new Date());
//	}
//
//	public String generateToken(UserDetails userDetails) {
//		Map<String, Object> claims = new HashMap<>();
//		claims.put("roles", userDetails.getAuthorities());
//		return doGenerateToken(claims, userDetails.getUsername());
//	}
//
//	private String doGenerateToken(Map<String, Object> claims, String subject) {
//     return Jwts.builder()
//               .setClaims(claims)
//              .setSubject(subject)
//               .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
//                .signWith(SignatureAlgorithm.HS512, secretKey)
//                .compact();
//		return Jwts.builder().claims().add(claims).subject(subject).issuedAt(new Date(System.currentTimeMillis()))
//				.expiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000)).and().signWith(getKey())
//				.compact();
//	}
//
//	public Boolean validateToken(String token, UserDetails userDetails) {
//		final String username = getUsernameFromToken(token);
//		return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
//	}
//}

//------------------------------------------------------
@Component
public class JwtUtil {

	private static final long serialVersionUID = 654352132132L;
	public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60; // 5 hours
	private final String secretKey = "randomkey123bnnqwertyuiopbngh48889888989849wertyuio4478";

//	public String generateToken(UserDetails userDetails) {
//		Map<String, Object> claims = new HashMap<>();
//		claims.put("roles", userDetails.getAuthorities());
//		return doGenerateToken(claims, userDetails.getUsername());
//	}
//
//	private String doGenerateToken(Map<String, Object> claims, String subject) {
//     
//		return Jwts.builder().claims().add(claims).subject(subject).issuedAt(new Date(System.currentTimeMillis()))
//				.expiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000)).and().signWith(getKey())
//				.compact();
//	}
	public String generateToken(UserDetails userdetails)
	{
		Map<String,Object> claims=new HashMap<>();
		//claims.put("roles", userdetails.getAuthorities());
		return doGenerateToken(claims, userdetails.getUsername());
	}
	
	public String doGenerateToken(Map<String,Object> claims, String subject)
	{
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(subject)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+JWT_TOKEN_VALIDITY * 1000))
				.signWith(getKey(), SignatureAlgorithm.HS256).compact();
//		return Jwts.builder().claims().add(claims).subject(subject).issuedAt(new Date(System.currentTimeMillis()))
//				.expiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000)).and().signWith(getKey(), SignatureAlgorithm.HS256)
//				.compact();
	}
	public String getUsername(String token) {
		return getClaim(token, Claims::getSubject);
	}

	public <T> T getClaim(String token, Function<Claims, T> claimResolver) {
		final Claims claims = getAllClaims(token);
		return claimResolver.apply(claims);
	}

	private Claims getAllClaims(String token) {
		return Jwts
				.parser()
				.setSigningKey(getKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
		
	}

	private Key getKey() {
	byte[] arr = Decoders.BASE64.decode(secretKey);
	return Keys.hmacShaKeyFor(arr);
		//return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
	}

	public boolean validateToken(String token, UserDetails user) {
		String username = getUsername(token);
		return (username.equals(user.getUsername()) && !isTokenExpired(token));
	}

	private boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	private Date extractExpiration(String token) {
		return getClaim(token, Claims::getExpiration);
	}

}