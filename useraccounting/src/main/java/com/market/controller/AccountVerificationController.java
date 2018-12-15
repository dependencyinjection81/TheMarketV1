package com.market.controller;

import com.market.beans.VcodeForm;
import com.market.repositories.StaticBlackListOnRuntime;
import com.market.security.service.DataPrepareService;
import com.market.service.UserService;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
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
public class AccountVerificationController implements WebMvcConfigurer {

  @Autowired
  private UserService userService;

  @Autowired
  StaticBlackListOnRuntime blacklist;

  @GetMapping(value = "/signup-verification")
  public String showForm(final VcodeForm verificationCodeForm) {
    return "signup-verification";
  }

  /**
   * Post controller account verification form.
   * @param verificationCodeForm Form
   * @param bindingResult Binding result
   * @param action Action
   * @param request Request
   * @return
   */
  @PostMapping(value = "/signup-verification")
  public String processForm(final @Valid VcodeForm verificationCodeForm, 
      final BindingResult bindingResult,
      final @RequestParam(value = "action", required = true) String action,
      final WebRequest request) {

    Authentication auth = SecurityContextHolder.getContext().getAuthentication();

    if (bindingResult.hasErrors() && action.equals("signup-verification")) {

      String ipAddress = ((WebAuthenticationDetails) SecurityContextHolder.getContext()
          .getAuthentication().getDetails()).getRemoteAddress();

      if (!blacklist.addIp(ipAddress)) { // IP Address was already blacklisted
        blacklist.increaseCounter(ipAddress); // Increase counter

        if (blacklist.getIpCounter(ipAddress) <= 5) { // Check counter
          bindingResult.rejectValue("c7", "VcodeForm.vcode.wrong.message"); // If counter ok
          return "signup-verification";
        } else {

          return "logout"; // If counter not ok
        }

      } else { // If this ipAddress has been added for the first time
        bindingResult.rejectValue("c7", "VcodeForm.vcode.wrong.message");
        return "signup-verification";
      }

    } else if (!bindingResult.hasErrors() && action.equals("signup-verification")) {

      if (auth == null) {
        /* TODO try to remember client and blacklist but this cannot be happen */

      } else {

        /* Try to validate token */
        String currentUsername = auth.getName();

        String incomingToken = new DataPrepareService(verificationCodeForm).getToken();

        int status = userService.verifyUser(currentUsername, incomingToken, auth);

        if (status == 1) {
          bindingResult.rejectValue("c7", "VcodeForm.vcode.wrong.message");
          return "signup-verification";

        } else if (status == 2) {
          bindingResult.rejectValue("c7", "VcodeForm.vcode.expired.message");
          return "signup-verification";

        } else if (status == 0) {
          
          
          
          
          return "redirect:welcome"; // TODO Verification success page
        }

      }

      return "logout"; // TODO cut off client and reject everything!
    }

    return "logout"; // TODO cut off client and reject everything!
  }

}
