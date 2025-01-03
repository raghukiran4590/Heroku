package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.api.response.WeatherResponse;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserEntryRepository;
import net.engineeringdigest.journalApp.service.UserService;
import net.engineeringdigest.journalApp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

    @RestController
    @RequestMapping("/user")
    public class UserController {

        @Autowired
        private UserService userService;

        @Autowired
        private UserEntryRepository userEntryRepository;

        @Autowired
        private WeatherService weatherService;

        @GetMapping
        public ResponseEntity<?> greeting() {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            WeatherResponse weatherResponse = weatherService.getWeather("Chicago");
            String greeting = "";
            if (weatherResponse != null) {
                greeting = ", Weather feels like "+ weatherResponse.getCurrent().getFeelsLike();
                return new ResponseEntity<>("Hi! "+authentication.getName() + greeting, HttpStatus.OK);
            }
            return new ResponseEntity<>("Hi! "+authentication.getName() + greeting, HttpStatus.OK);
        }

        @PutMapping
        public ResponseEntity<?> updateUser(@RequestBody User user) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();
            User userInDb = userService.findByUserName(userName);
            userInDb.setUserName(user.getUserName());
            userInDb.setPassword(user.getPassword());
            userService.saveNewUser(userInDb);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        @DeleteMapping
        public ResponseEntity<?> deleteByUserName() {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            userEntryRepository.deleteByUserName(authentication.getName());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

/*
        @PostMapping("/postAll")
        public ResponseEntity<?> createUsers(@RequestBody List<User> users) {
            try {
                for (User u : users) {
                    userService.saveEntry(u);
                }
                return new ResponseEntity<>(users, HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
*/
    }

