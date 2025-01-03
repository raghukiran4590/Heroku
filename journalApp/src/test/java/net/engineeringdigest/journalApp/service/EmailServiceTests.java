package net.engineeringdigest.journalApp.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTests {

    @Autowired
    private EmailService emailService;

    @Disabled
    @Test
    public void testSendEmail() {
        emailService.sendEmail("raghukiran4590@gmail.com",
                                            "Java Spring Test Email",
                                            "Hi! How are you doing?");
    }

}
