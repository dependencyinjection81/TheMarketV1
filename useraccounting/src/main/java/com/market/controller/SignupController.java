package com.market.controller;

import com.market.beans.UserForm;
import com.market.entities.User;
import com.market.events.OnRegistrationCompleteEvent;
import com.market.repositories.UserRepository;
import com.market.security.validation.EmailValidator;
import com.market.security.validation.PasswordValidator;
import com.market.security.validation.UsernameValidator;
import com.market.service.UserService;

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
    return "signup";
  }

  /**
   * Controls the flow of registering a new user.
   * 
   * @param userForm      user form
   * @param bindingResult binding result
   * @param action        action
   * @param request       request
   * @return
   */
  @PostMapping(value = "/signup")
  public String processForm(final UserForm userForm, /* user form bean */
      final BindingResult bindingResult, /* result to handle or process errors */
      final @RequestParam(value = "action", required = true) String action,
      /* additional parameter because I have also a cancel button in my form */
      final WebRequest request) {

    /* Try to validate username field */
    String usernameValidatorStatus = usernameValidator.validateUsername(userForm.getUname());
    
    if (usernameValidatorStatus != null) {
      bindingResult.rejectValue("uname", usernameValidatorStatus);
      return "signup";
    } else {

      /* Try to validate email field */
      String emailValidatorStatus = emailValidator.valdateEmail(userForm.getEmail());

      if (emailValidatorStatus != null) {
        bindingResult.rejectValue("email", emailValidatorStatus);
        return "signup";
      } else {

        /* Try to validate password field */
        String passwordValidatorStatus = passwordValidator.validatePassword(userForm.getPwd());

        if (passwordValidatorStatus != null) {
          bindingResult.rejectValue("pwd", passwordValidatorStatus);
          return "signup";
        } else {
          
          /* Try to validate passwordConfirm field */
          String passwordFieldMatchStatus = passwordValidator.fieldMatch(userForm.getPwd(),
              userForm.getPwdConfirm());

          if (passwordFieldMatchStatus != null) {
            bindingResult.rejectValue("pwdConfirm", passwordFieldMatchStatus);
            return "signup";
          } else {

            /* Try to register new user */
            int status = userService.registerNewUserAccount(userForm);

            if (status == 1) {
              bindingResult.rejectValue("uname", "UserForm.uname.UnameInUse.message");
              return "signup";

            } else if (status == 2) {
              bindingResult.rejectValue("email", "UserForm.email.EmailInUse.message");
              return "signup";

            } else if (status == 0) {
              String appUrl = request.getContextPath();
              User user = userRepository.findByEmail(userForm.getEmail());

              eventPublisher
                  .publishEvent(new OnRegistrationCompleteEvent(user, request.getLocale(), appUrl));
              return "index"; // TODO registration success page und verification Hinweis
            }
          }
        }
      }
    }
    return "index";
  }

}
