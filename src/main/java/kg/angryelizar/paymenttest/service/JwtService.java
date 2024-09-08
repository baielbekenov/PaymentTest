package kg.angryelizar.paymenttest.service;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;

@Service
public interface JwtService {
    String extractUsername(String token);
    String generateToken(UserDetails userDetails);
    Boolean isTokenValid(String token, UserDetails userDetails);
    String generateToken(Map<String, Object> claims, UserDetails userDetails);
    Boolean isTokenExpired(String token);
    Date extractExpirationDate(String token);
    Claims extractAllClaimsFromToken(String token);
    Key getSigningKey();

    String makeTokenInvalid(HttpServletRequest request, Authentication authentication);
}
