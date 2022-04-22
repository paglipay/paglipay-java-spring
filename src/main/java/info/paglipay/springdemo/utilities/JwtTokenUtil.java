package info.paglipay.springdemo.utilities;
import io.jsonwebtoken.*;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenUtil {
  private final String jwtSecret = "jwtSecret";

  private final String jwtIssuer = "jwtIssuer";

//  JwtTokenUtil(@Autowired Environment environment) {
//    this.jwtSecret = environment.getProperty("aa2228b097e40541dfb229bacf40f00a81eac92a1b11412c4d678cb99feba0e96187db65b4b28eefd90097aa3b668790189dc1635823b7bb44cc5b4abdc61c37");
//    this.jwtIssuer = environment.getProperty("com.revature.jwtissuer");
//  }

  public String generateAccessToken(User user) {
    return Jwts
      .builder()
      .setSubject(user.getUsername())
      .setIssuer(jwtIssuer)
      .setIssuedAt(new Date())
      .setExpiration(
        new Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000)
      ) // 1 week
      .signWith(SignatureAlgorithm.HS512, jwtSecret) // Signed with the secret
      .compact();
  }

  public String getUsername(String token) {
    Claims claims = Jwts
      .parser()
      .setSigningKey(jwtSecret)
      .parseClaimsJws(token)
      .getBody();

    return claims.getSubject();
  }

  public Date getExpirationDate(String token) {
    Claims claims = Jwts
      .parser()
      .setSigningKey(jwtSecret)
      .parseClaimsJws(token)
      .getBody();

    return claims.getExpiration();
  }

  public boolean validate(String token) {
    try {
      Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
      return true;
    } catch (SignatureException ex) {
      System.err.println("Invalid JWT signature");
      System.err.println(ex.getMessage());
    } catch (MalformedJwtException ex) {
      System.err.println("Malformed JWT");
      System.err.println(ex.getMessage());
    } catch (ExpiredJwtException ex) {
      System.err.println("Expired JWT signature");
      System.err.println(ex.getMessage());
    } catch (IllegalArgumentException ex) {
      System.err.println("JWT claims string is empty");
      System.err.println(ex.getMessage());
    }
    return false;
  }
}