package com.market.controller;

import com.market.beans.UserForm;
import com.market.entities.UserEntity;
import com.market.service.UserService;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Controller
@RequestMapping(value = "/")
public class SignupController implements WebMvcConfigurer {
  
  @Autowired
  private UserService userService;

  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/signupconfirm").setViewName("signupconfirm");
  }

  /**
   * show the signup-form and binds the validation bean to it.
   * 
   * @return signup.html
   */
  @GetMapping(value = "/signup_responsive")
  public String showForm(UserForm userForm) {
    return "signup_responsive";

  }
  
  /**
   * Fetch data.
   * 
   * @param userForm
   *          userForm bean
   * @param bindingResult
   *          bindingResult
   * @param action
   *          action
   * @return
   */
  @PostMapping(value = "/action")
  public String checkFormData(final @Valid UserForm userForm, final BindingResult bindingResult,
      final @RequestParam(value = "action", required = true) String action) {

    if (bindingResult.hasErrors() && action.equals("verify")) {
      return "signup_responsive";
    } else if (action.equals("cancel")) {
      return "index";
    } else {
      
      
      /**
       * Populating the user entity.
       */
      UserEntity usr = new UserEntity();
      usr.setUserName(userForm.getUname());
      usr.setEmail(userForm.getEmail());
      usr.setPwd(userForm.getPwd());
      usr.setEnabled(false);
      usr.setId();
      System.out.println(userForm.getUname());
      System.out.println(userForm.getEmail());
      System.out.println(userForm.getPwd());
      
      /**
       * Save entity to repository
       */
      userService.saveUser(usr);
      
      return "signupconfirm";
    }
  }

}
