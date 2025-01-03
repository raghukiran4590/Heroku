package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserEntryRepository;
import org.junit.jupiter.api.*;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

//@SpringBootTest
// By using @SpringBootTest this is tested by loading the Springboot application context,
// hence the whole application and server will be started
public class UserDetailsServiceImplTests {

//    @Autowired
    @InjectMocks
    private UserDetailsServiceImpl userDetailsServiceImpl;

//    @MockBean
    @Mock
    private UserEntryRepository userEntryRepository;

    @BeforeEach
    void setUp() {
//        MockitoAnnotations.initMocks(this);
        MockitoAnnotations.openMocks(this);
    }

@Disabled
    @Test
    void loadUserByUsernameTest() {
        when(userEntryRepository.findByUserName(ArgumentMatchers.anyString())).thenReturn(User.builder().userName("ram").password("iehfksjdbfb").roles(new ArrayList<>()).build());
        UserDetails user = userDetailsServiceImpl.loadUserByUsername("ram");
        Assertions.assertNotNull(user);

    }
}
