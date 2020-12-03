package kr.co.study.delivery.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;


public class JwtUtil {

    private Key key;

    // secret을 받으면 그걸 바이트로 얻어서 암호화!
    public JwtUtil(String secret) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    // token 생성하기
    public String createToken(Long userId, String name, Long restaurantId) {

        // 자바 내장 Key와 Jwt의 Keys. hmac에서 SHA의 키를 쓸거다 byte로 얻어서.
        // 근데 256 할려면 32글자로 맞춰야 함.
        // 문자열로 key 를 만들어주고, 그 key로 사인을 한다!

        JwtBuilder builder = Jwts.builder()
                .claim("userId", userId)
                .claim("name", name);

                // 만약 레스토랑 아이디가 있는 레스토랑 주인이라면 claim에 레스토랑 아이디도 추가
                if(restaurantId != null){

                    builder = builder.claim("restaurantId", restaurantId);
                    
                }


        return builder
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

    }


    public Claims getClaims(String token) {

        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)       // sign이 포함된 jwt
                .getBody();

    }
}
