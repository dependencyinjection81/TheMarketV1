package com.market.controller;

import com.market.beans.UserForm;
import com.market.beans.VcodeForm;
import com.market.entities.UserEntity;
import com.market.security.service.TokenService;
import com.market.service.UserService;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Controller
@RequestMapping(value = "/")
public class SignupController implements WebMvcConfigurer {
  
  @Autowired
  private UserService userService;

  
  /**
   * show the signup-form and binds the validation bean to it.
   * 
   * @return signup.html
   */
  @GetMapping(value = "/signup")
  public String showForm(UserForm userForm) {
    return "signup";

  }
  
  /**
   * show the vcode-form and binds the validation bean to it.
   * 
   * @return signup.html
   */
  @GetMapping(value = "/vcode")
  public String showForm(VcodeForm vcodeform) {
    return "vcode";

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

    if (bindingResult.hasErrors() && action.equals("signup")) {
      return "signup";
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
      // Disable user until they confirm the verification code sent by Email.
      usr.setEnabled(false);
      // Generate random 8-character verification code.  
      usr.setConfirmationToken(new TokenService(8).getToken());
      
      /**
       * Save entity to repository
       */
      userService.saveUser(usr);
      
      return "vcode";
    }
  }

}
