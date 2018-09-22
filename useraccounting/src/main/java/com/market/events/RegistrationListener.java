package com.market.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.market.entities.User;
import com.market.security.service.TokenService;
import com.market.service.UserService;

@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

  @Autowired
  private UserService userService;

  @Autowired
  private JavaMailSender mailSender;

  @Override
  public void onApplicationEvent(final OnRegistrationCompleteEvent event) {
    this.confirmRegistration(event);
  }

  private void confirmRegistration(final OnRegistrationCompleteEvent event) throws MailException {
    final User user = event.getUser();
    final String token = new TokenService().getToken();
    userService.createVerificationTokenForUser(user, token);

    final SimpleMailMessage email = constructEmailMessage(user, token);
    mailSender.send(email);
    System.out.println("sent mail to: " + user.getEmail());
  }

  private final SimpleMailMessage constructEmailMessage(final User user, final String token) {
    final String recipientAddress = user.getEmail();
    final String subject = "Registration Confirmation";
    final String confirmationUrl = "http://localhost:8080/login";
    final String message = "Hello " + user.getUsername() + "!" + "\n"
                         + "You just have been registered to our site" + "\n"
                         + "In order to activate your account you have to verify this e-mail." + "\n"
                         + "To do this just remember or write down the following verification code below" + "\n"
                         + "and enter it when you login for the first time" + "\n";
    final SimpleMailMessage email = new SimpleMailMessage();
    email.setTo(recipientAddress);
    email.setSubject(subject);
    email.setText(message + "Verifiaction-code: " + token + "\n" + confirmationUrl);
    email.setFrom("springbootdev@gmx.de");
    return email;
  }

}
