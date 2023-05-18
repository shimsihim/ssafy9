package com.ssaffy.ssafit.util;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;


@Component
public class JwtUtil {
	private static final String SALT = "SSAFIT";
	
	
	public String createToken(String claim) throws UnsupportedEncodingException {
		
		
		return Jwts.builder().setHeaderParam("alg","HS256").setHeaderParam("typ","JWT")//헤더 완료
				.claim("userId","ssafy").setExpiration(new Date(System.currentTimeMillis()+3000))
				.signWith(SignatureAlgorithm.HS256,"SSAFIT".getBytes("UTF-8"))//서명 완료
				.compact();
	}
	
	public void valid(String token) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException, UnsupportedEncodingException {
		Jwts.parser().setSigningKey("SSAFIT".getBytes("UTF-8")).parseClaimsJws(token);
	}
}	
