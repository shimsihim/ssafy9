package com.ssafy.ssafit.util;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;



@Component
public class JwtUtil {
    private static final String SALT = "SSAFIT";
    
    
    public String createToken(String claim, String data) throws UnsupportedEncodingException {
        //토큰 생성
        
        return Jwts.builder().setHeaderParam("alg","HS256").setHeaderParam("typ","JWT")//헤더 완료
                .claim("userId",data)/*.setExpiration(new Date(System.currentTimeMillis()+100000000))*/ // 시간은 현재 임의로 정함
                .signWith(SignatureAlgorithm.HS256,"SSAFIT".getBytes("UTF-8"))//서명 완료
                .compact();
    }
    
    public void valid(String token) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException, UnsupportedEncodingException {
        //토큰의 유효성을 검사함
        Jwts.parser().setSigningKey("SSAFIT".getBytes("UTF-8")).parseClaimsJws(token);
    }
    
    public String parse(String token) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException, UnsupportedEncodingException { 
        // user의 아이디 받아옴
        Jws<Claims> claims = Jwts.parser().setSigningKey(SALT.getBytes("UTF-8")).parseClaimsJws(token);
        return (String) claims.getBody().get("userId");
    }
}  