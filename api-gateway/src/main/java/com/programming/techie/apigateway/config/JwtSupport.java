//package com.programming.techie.apigateway.config;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.JwtParser;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//
//import java.security.Key;
//import java.sql.Date;
//import java.time.Instant;
//import java.time.temporal.ChronoUnit;
//
//@Component
//public class JwtSupport {
////    private final static String SECRET_KEY = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";
////
////    byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
//    private Key key = Keys.hmacShaKeyFor("bgQVeFfKwr5uy3N3QowEIrNLqbhgvfQ/+HVupvuH7io=".getBytes());
//    private JwtParser parser = Jwts.parserBuilder().setSigningKey(key).build();
//
//    public BearerToken generate(String username){
//        var builder = Jwts.builder().setSubject(username)
//                .setIssuedAt(Date.from(Instant.now()))
//                .setExpiration(Date.from(Instant.now().plus(15, ChronoUnit.MINUTES) ))
//                .signWith(key);
//        return new BearerToken(null, builder.compact());
//    }
//
//    public String getUsername(BearerToken token){
//       return parser.parseClaimsJws(token.getValue()).getBody().getSubject();
//    }
//
//    public Boolean isValid(BearerToken token, UserDetails user){
//        Claims claims = parser.parseClaimsJws(token.getValue()).getBody();
//        var unexpired = claims.getExpiration().after(Date.from(Instant.now()));
//
//        return unexpired && (claims.getSubject().equals(user.getUsername()));
//    }
//}
//
