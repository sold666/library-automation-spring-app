package main.security.JWT;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "jwt")
public class JWTProperties {

    private String secretKey = "verySecretKey";
    private long validityMS = 180000;

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public long getValidityMS() {
        return validityMS;
    }

    public void setValidityMS(long validityMS) {
        this.validityMS = validityMS;
    }
}
