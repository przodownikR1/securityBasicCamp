package pl.java.scalatech.entity;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordListener {
    private PasswordEncoder encoder = new BCryptPasswordEncoder();

    @PrePersist
    @PreUpdate
    void encryptPassword(User user) {
        String password = user.getPassword();
        if (password != null) {
            String passwordHash = encoder.encode(password);
            user.setPassword(passwordHash);
        }
    }
}
