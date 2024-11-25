package kg.bbekenov.projecttest.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import kg.bbekenov.projecttest.models.Session;
import kg.bbekenov.projecttest.models.User;
import kg.bbekenov.projecttest.repository.SessionRepository;
import kg.bbekenov.projecttest.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {
    private final SessionRepository sessionRepository;
    @Value("${token.signing.key}")
    private String signingKey;

    @Override
    public String extractUsername(String token) {
        return extractAllClaimsFromToken(token).getSubject();
    }

    @Override
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        if (userDetails instanceof User customUserDetails) {
            claims.put("id", customUserDetails.getId());
            claims.put("phone", customUserDetails.getUsername());
            claims.put("role", customUserDetails.getAuthorities());
        }
        return generateToken(claims, userDetails);
    }

    @Override
    public Boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private <T> T extractAllClaimsFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    @Override
    public String generateToken(Map<String, Object> claims, UserDetails userDetails) {
        return Jwts.builder().claims(claims).subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 100000 * 60 * 24))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256).compact();
    }

    @Override
    public Boolean isTokenExpired(String token) {
        return extractExpirationDate(token).before(new Date());
    }

    @Override
    public Date extractExpirationDate(String token) {
        return extractAllClaimsFromToken(token).getExpiration();
    }

    @Override
    public Claims extractAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody();
    }

    @Override
    public Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(signingKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    @Override
    public String makeTokenInvalid(HttpServletRequest request, Authentication authentication) {
        String authHeader = request.getHeader("Authorization");
        String bearerPrefix = "Bearer ";
        String jwtToken = authHeader.substring(bearerPrefix.length());
        Session session = sessionRepository.findByToken(jwtToken).get();
        sessionRepository.delete(session);
        return "Logout done successfully for token " + jwtToken;
    }
}
