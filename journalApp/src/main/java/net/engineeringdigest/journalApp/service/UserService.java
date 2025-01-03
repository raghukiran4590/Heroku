package net.engineeringdigest.journalApp.service;

import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserEntryRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

    @Component
    @Slf4j
    public class UserService {

        @Autowired
        private UserEntryRepository userEntryRepository;

        private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

//        private static final Logger logger = LoggerFactory.getLogger(UserService.class);

        public List<User> getAll() {
            return userEntryRepository.findAll();
        }

        public Optional<User> findById(ObjectId myId) {
            return userEntryRepository.findById(myId);
        }

        public User findByUserName(String userName) {
            return userEntryRepository.findByUserName(userName);
        }

        public void saveUser(User user) {
            userEntryRepository.save(user);
        }

        public boolean saveNewUser(User user) {
            try {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                user.setRoles(Arrays.asList("USER"));
                userEntryRepository.save(user);
                return true;
            } catch (Exception e) {
                log.error("Error Occurred for User : {} ", user.getUserName(), e);
                log.info("Information Log");
                log.warn("Warning Log");
                log.debug("Debug Log");
                log.trace("Trace Log");
                return false;
            }
        }

        public void saveAdminUser(User user) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER", "ADMIN"));
            userEntryRepository.save(user);
        }
    }
