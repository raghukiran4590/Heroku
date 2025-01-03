package net.engineeringdigest.journalApp.service;


import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserEntryRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

enum Color {
    RED,
    GREEN,
    BLUE
}

@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserEntryRepository userEntryRepository;

    @Autowired
    private UserService userService;


    //For initializing or creating the resources required for all the tests
    @BeforeAll
    static void init() {
        System.out.println("initializing resources");
    }

    //For destroying or deleting the initialized or created resources in init() method
    @AfterAll
    static void destroy() {
        System.out.println("destroying resources");
    }

    @Disabled
    @Test
    public void testAdd() {
        assertEquals(4, 2 + 2);
    }

    @Disabled
    @Test
    public void testUserJournalEntries() {
        User user = userEntryRepository.findByUserName("ram");
        assertFalse(user.getJournalEntries().isEmpty());
    }

    @Disabled
    @ParameterizedTest
/*    @CsvSource({
            "Ram",
            "vriha",
            "vipul",
            "neetha",
            "raghu"
    })*/
//    @EnumSource(Color.RED)
    @ValueSource(strings = {
            "Ram",
            "vriha",
            "vipul",
            "neetha",
            "raghu"
    })
    public void testUserName(String userName) {
        assertNotNull(userEntryRepository.findByUserName(userName), "Failed for : " + userName);
    }

    @Disabled
    @ParameterizedTest
    @ArgumentsSource(UserArgumentsProvider.class)
    public void testSaveNewUser(User user) {
        assertTrue(userService.saveNewUser(user));
    }

    @Disabled
    @ParameterizedTest
    @CsvSource({
            "1,2,3",
            "2,10,12",
            "3,3,8"
    })
    public void testAdd2(int a, int b, int expected) {
        assertEquals(expected, a + b);
    }

}
