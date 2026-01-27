package ecommerce.core.user.security.jwt;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	private static final String SECRET_KEY = "9gnXRSI83lMTPiN7/EKpS2By30olCDQWCGyxX7e1fdVY9B6zAcedCmlIE2jf6OLMmM/3eFs4ysGvebpaq/Ze1A==";
	private final long expirationTime = 1000 * 60 * 60 * 24;
	
	public String generateToken(UserDetails userDetails) {
		SecretKey key = getSignInKey();
		Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + expirationTime);
		return Jwts.builder()
					.subject(userDetails.getUsername())
					.issuedAt(new Date(System.currentTimeMillis()))
					.expiration(expireDate)
					.signWith(key)
					.compact();
				
	}
	
	public String generateTokenOauth2(String username) {
		SecretKey key = getSignInKey();
		Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + expirationTime);
        return Jwts.builder().subject(username)
        		.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(expireDate)
				.signWith(key)
				.compact();
	}
	
	public String getUsernameFromToken(String token) {
		return extractAllClaims(token).getSubject();
	}
	
	public boolean validateToken(String token,UserDetails userDetails) {
		try {
			final String userMail = getUsernameFromToken(token);
			if(userMail.equals(userDetails.getUsername())) {
				return true;
			}
			return false;
		}catch(Exception e) {
			return false;
		}
	}
	
	private Claims extractAllClaims(String token) {
		SecretKey key = getSignInKey();
		return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();
	}
	
	private SecretKey getSignInKey() {
		byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
		SecretKey sKey = Keys.hmacShaKeyFor(keyBytes);
		return sKey;
	}
}
