package com.market.controller;

import com.market.beans.UserForm;
import com.market.entities.User;
import com.market.events.OnRegistrationCompleteEvent;
import com.market.repositories.UserRepository;
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
  private UserService userService;
  
  @Autowired
  private ApplicationEventPublisher eventPublisher;
  
  @Autowired
  private UserRepository userRepository;
 
  
  @GetMapping(value = "/signup")
  public String showForm(UserForm userForm) {
    return "signup_centered";
  }

  @PostMapping(value = "/signup")
  public String processForm(
      final @Valid UserForm userForm, /* user form bean */
      final BindingResult bindingResult, /* result to handle or process errors */
      final @RequestParam(value = "action", required = true) String action, 
      /* additional parameter because I have also a cancel button in my form */
      final WebRequest request) /**/
   {

    if (bindingResult.hasErrors() && action.equals("signup")) {
      return "signup_centered";
    } else if (action.equals("cancel")) {
      return "index";
    } else {
      //TODO Username proposal based on policy
     
      int status = userService.registerNewUserAccount(userForm);
      
      if (status == 1) {
        bindingResult.rejectValue("uname", "UserForm.uname.UnameInUse.message");
        return "signup_centered";
        
      } else if (status == 2) {
        bindingResult.rejectValue("email", "UserForm.email.EmailInUse.message");
        return "signup_centered";
      
      } else if (status == 0) {
        String appUrl = request.getContextPath();
        User user = userRepository.findByEmail(userForm.getEmail());
        
        eventPublisher.publishEvent(new OnRegistrationCompleteEvent(user, request.getLocale(), appUrl));
        return "index";  //TODO registration success page und verification Hinweis      
      }
      
    }
    
    return "index";
  
   }
  
}
