package com.market.controller;

import com.market.beans.LoginForm;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Controller
@RequestMapping(value = "/")
public class LoginController implements WebMvcConfigurer {

 

  /**
   * show the login-page and binds the validation bean to it.
   * 
   * @return login.html
   */

  @GetMapping(value = "/login")
  public String showForm(LoginForm loginForm) {
    return "login";
  }

  /**
   * Fetch data.
   * @param loginForm backed bean.
   * @param bindingResult errors.
   * @param action login or cancel.
   * @return
   */
  @PostMapping(value = "/login")
  public String checkFormData(final @Valid LoginForm loginForm, /* user form bean */
      final BindingResult bindingResult, /* result to handle or process errors */
      final @RequestParam(value = "action", required = true) String action)
  /* additional parameter because I have also a cancel button in my form */ {

    /**
     * STEP 1 FORM VALIDATION
     * 
     * If the user hit the signupButton and the bindingResult has errors return the same page.
     * Errors will be parsed and displayed automatically.
     */
    if (bindingResult.hasErrors() && action.equals("login")) {
      return "login";
    } else if (action.equals("cancel")) {
      return "index";
    } else {
      
      /*TODO Check email-adress */
      /*TODO if email exist exist redirect to enter password */
      /*TODO Check username */
      return "signup";
    }
  }
}
