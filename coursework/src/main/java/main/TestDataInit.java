package main;

import main.repository.JournalRepository;
import main.repository.UserRepository;
import main.tables.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;

@Component
public class TestDataInit implements CommandLineRunner {

    @Autowired
    JournalRepository journalRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        Optional<User> checkUser = userRepository.findUserByUserName("librarian");
        if (checkUser.isEmpty())
            userRepository.save(new User("librarian", passwordEncoder.encode("library"), Collections.singletonList("ROLE_LIBRARIAN")));
    }
}
