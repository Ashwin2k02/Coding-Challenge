package com.springboot.jwtsecurity;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component //this registers jwt utility with spring
public class JwtUtil {

	private String SECRET_KEY ="secret";
	
	//this method is used to extract username from the token that will come from the UI
	//this happens automatically with spring security HTTP Basic 
	public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }
	
	//this method will set the expiry date of the toke, usually it is 3 days (3*24*60*60)
	public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
	
	//this method resolves the claim placed by the UI developer while giving the token
	//this claim must also be registered with overall Claims class
	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		//extract the claims basis given token
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
	
	
	//this method first encodes the secret key using base64 encoder and parses it to create a stronger token
	//such methods are sometimes called as helper methods
	private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(Base64.getEncoder().encodeToString(SECRET_KEY.getBytes())).parseClaimsJws(token).getBody();
    }
	
	private String createToken(Map<String, Object> claims, String subject) {
		 
        return Jwts.builder() //this is called as builder design pattern which we are using to build JWTS object
                .setClaims(claims) //claim has a key and value pair
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() *3*24*60*60*1000))
                 .signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encodeToString(SECRET_KEY.getBytes()))
                .compact();
    }
	
	//this will generate the token basis username
	public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username);
    }
	
	//checks whether the token is valid mor expired
	private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
	
	//this is to validate the token when it comes from the UI
	public Boolean validateToken(String token, String username) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }
}
