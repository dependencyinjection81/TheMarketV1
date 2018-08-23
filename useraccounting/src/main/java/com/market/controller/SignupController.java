package com.market.controller;

import com.market.beans.UserForm;
import com.market.beans.VcodeForm;
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
  @PostMapping(value = "/signup")
  public String checkFormData(final @Valid UserForm userForm, /* user form bean */
      final BindingResult bindingResult, /* result to handle or process errors */
      final @RequestParam(value = "action", required = true) String action)
  /* additional parameter because I have also a cancel button in my form */ {

    /**
     * STEP 1 FORM VALIDATION
     * 
     * If the user hit the signupButton and the bindingResult has errors return the same page.
     * Errors will be parsed and displayed automatically.
     */
    if (bindingResult.hasErrors() && action.equals("signup")) {
      return "signup";
    } else if (action.equals("cancel")) {
      return "index";
    } else {

      /**
       * STEP 2 TRY TO REGISTER THE NEW USER
       * the returned value will be 0 or 1 or 3 if there was an error like
       * 
       * 1 username is already in use
       * 2 email is already in use
       * 0 if everything was ok and the account has been registered
       *   
       */
      if (userService.registerNewUserAccount(userForm) == 1) {
        bindingResult.rejectValue("uname", "UserForm.uname.UnameInUse.message");
        return "signup";
      } else if (userService.registerNewUserAccount(userForm) == 2) {
        bindingResult.rejectValue("email", "UserForm.email.EmailInUse.message");
        return "signup"; 
      }
      
    }

    return "index";
  }
  
}
