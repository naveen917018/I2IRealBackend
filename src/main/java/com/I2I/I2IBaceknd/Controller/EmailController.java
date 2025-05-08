package com.I2I.I2IBaceknd.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.I2I.I2IBaceknd.Service.EmailService;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/send-email")
    public String sendEmail() {
        emailService.sendEmail("adarshmuralip@gmail.com", " Mail Checking ", "If you're looking for dummy data to simulate an image or any kind of payload for email testing, here’s an example of a simple payload that you can use to test:");
        return "Email sent successfully!";
    }
}
