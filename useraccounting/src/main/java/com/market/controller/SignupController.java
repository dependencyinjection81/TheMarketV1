package com.market.controller;

import com.market.beans.UserForm;
import com.market.entities.User;
import com.market.events.OnRegistrationCompleteEvent;
import com.market.repositories.UserRepository;
import com.market.security.validation.EmailValidator;
import com.market.security.validation.PasswordValidator;
import com.market.security.validation.UsernameValidator;
import com.market.service.UserService;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Controller
@RequestMapping(value = "/")
public class SignupController implements WebMvcConfigurer {

  @Autowired
  private UsernameValidator usernameValidator;

  @Autowired
  EmailValidator emailValidator;
  
  @Autowired
  PasswordValidator passwordValidator;

  @Autowired
  private UserService userService;

  @Autowired
  private ApplicationEventPublisher eventPublisher;

  @Autowired
  private UserRepository userRepository;

  @GetMapping(value = "/signup")
  public String showForm(UserForm userForm) {
    return "signup_centered";
  }

  /**
   * Controls the flow of registering a new user.
   * @param userForm user form
   * @param bindingResult binding result
   * @param action action
   * @param request request
   * @return
   */
  @PostMapping(value = "/signup")
  public String processForm(final @Valid UserForm userForm, /* user form bean */
      final BindingResult bindingResult, /* result to handle or process errors */
      final @RequestParam(value = "action", required = true) String action,
      /* additional parameter because I have also a cancel button in my form */
      final WebRequest request) {

    int usernameValidatorStatus = usernameValidator.validateUsername(userForm.getUname());

    if (usernameValidatorStatus == 1) {
      bindingResult.rejectValue("uname", "UserForm.uname.NotBlank.message");
      return "signup_centered";
    } else if (usernameValidatorStatus == 2) {
      bindingResult.rejectValue("uname", "UserForm.uname.Whitelist.message");
      return "signup_centered";
    } else if (usernameValidatorStatus == 3) {
      bindingResult.rejectValue("uname", "UserForm.uname.TooShort.message");
      return "signup_centered";
    } else if (usernameValidatorStatus == 4) {
      bindingResult.rejectValue("uname", "UserForm.uname.TooLong.message");
      return "signup_centered";
    } else if (usernameValidatorStatus == 0) {

      int emailValidatorStatus = emailValidator.valdateEmail(userForm.getEmail());

      if (emailValidatorStatus == 1) {
        bindingResult.rejectValue("email", "UserForm.email.NotBlank.message");
        return "signup_centered";
      } else if (emailValidatorStatus == 2) {
        bindingResult.rejectValue("email", "UserForm.email.Email.message");
        return "signup_centered";
      } else if (emailValidatorStatus == 0) {
        
        int passwordValidatorStatus = passwordValidator.validatePassword(userForm.getPwd());
        
      }
    }

    return "index";

  }

}
