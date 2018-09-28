package com.market.controller;

import com.market.beans.UserForm;
import com.market.beans.VcodeForm;
import com.market.entities.User;
import com.market.events.OnRegistrationCompleteEvent;
import com.market.repositories.UserRepository;
import com.market.security.service.DataPrepareService;
import com.market.service.UserService;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
  ApplicationEventPublisher eventPublisher;
  
  @Autowired
  UserRepository userRepository;
   
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
   * show the verificationform and binds the validation bean to it.
   * 
   * @return verification.html
   */
  @GetMapping(value = "/verification")
  public String showForm(VcodeForm vCodeForm) {
    return "verification";
  }
  
  
  @PostMapping(value = "/verification")
  public String checkFormData(final @Valid VcodeForm vCodeForm, /* verificationForm bean */
      final BindingResult bindingResult, /* result to handle or process errors */
      final @RequestParam(value = "action", required = true) String action,
      /* additional parameter because I have also a cancel button in my form */
      final WebRequest request) /**/ {

    /**
     * STEP 1 FORM VALIDATION
     * 
     * If the user hit the signupButton and the bindingResult has errors return the same page.
     * Errors will be parsed and displayed automatically.
     */
    if (bindingResult.hasErrors() && action.equals("verification")) {
      
      return "verification";

    } else if (!bindingResult.hasErrors() && action.equals("verification")) {
      
      /**
       * Determine the current logged in username.
       */
      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      if (auth == null) {
        /**
         * if this case happned something very unexpected going on because no unauthenticated client  
         * should be able to call this controller.
         *TODO try to remember client and blacklist
         */
      } else {
        
        String currentUsername = auth.getName();  
        
        /**
         * STEP 2 TRY TO Verify the given token.
         * the returned value will be 0 or 1 or 2 or 3 if there was an error as described below.
         * 
         * 1 wrong token
         * 2 token expired
         * 0 successful
         *   
         */
        String incomingToken = new DataPrepareService(vCodeForm).getToken();
        int status = userService.verifyUser(currentUsername, incomingToken);
        
        if (status == 1) {
          bindingResult.rejectValue("c1", "VcodeForm.vcode.wrong.message");
          return "verification";
        
        } else if (status == 2) {
          bindingResult.rejectValue("c1", "VcodeForm.vcode.expired.message");
          return "verification";
          
        } else if (status == 0) {
          return "welcome"; //TODO Verification success page
        }
      
      }
            
      return null; //TODO cut off client and reject anything!
    }
    
    return null; //TODO cut off client and reject anything!

    

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
  public String checkFormData(
      final @Valid UserForm userForm, /* user form bean */
      final BindingResult bindingResult, /* result to handle or process errors */
      final @RequestParam(value = "action", required = true) String action, 
      /* additional parameter because I have also a cancel button in my form */
      final WebRequest request) /**/
   {

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
       * 2 email is already in use
       * 1 username is already in use
       * 0 if everything was ok and the account has been registered
       *   
       */
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
        
        eventPublisher.publishEvent(new OnRegistrationCompleteEvent(user, request.getLocale(), appUrl));
        return "index";        
      }
      
    }
    
    return "index";
  
   }
  
}
