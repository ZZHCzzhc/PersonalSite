package com.hr.authenticationservice.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class JwtProvider {

    @Value("${security.jwt.token.key}")
    private String key;
    @Value("${security.jwt.token.jwtExpirationInMs}")
    private int jwtExpirationInMs;


    // create jwt from a UserDetail
    public String createToken(AuthUserDetail userDetails) {

        Claims claims = Jwts.claims().setSubject(Integer.toString(userDetails.getUserid())); // user identifier
        claims.put("Role", userDetails.getAuthorities()); // user Role

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationInMs))
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }

    public Optional<AuthUserDetail> resolveToken(HttpServletRequest request) {
        String prefixedToken = request.getHeader("Authorization"); // extract
        if (prefixedToken != null) {
            String token = prefixedToken.substring(0);

            Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody(); // decode

            int id = Integer.parseInt(claims.getSubject());
            List<LinkedHashMap<String, String>> permissions = (List<LinkedHashMap<String, String>>) claims.get("Role");

            List<GrantedAuthority> authorities = permissions.stream()
                    .map(p -> new SimpleGrantedAuthority(p.get("authority")))
                    .collect(Collectors.toList());

            return Optional.of(AuthUserDetail.builder()
                    .userid(id)
                    .authorities(authorities)
                    .build());

        }
        return Optional.of(AuthUserDetail.builder()
                .build());
    }

    public int GetIdByResolveToken(String header) {
        String token = header.substring(0);

        Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody(); // decode

        int id = Integer.parseInt(claims.getSubject());
        System.out.println("claims= " + claims);
        return id;

    }
}
