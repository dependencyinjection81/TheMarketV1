package com.market.controller;

import com.market.beans.RequestForm;
import com.market.security.validation.RequestValidator;
import com.market.service.RequestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;



@Controller
@RequestMapping(value = "/")
public class RequisitionController {
  
  @Autowired
  RequestValidator requestValidator;
  
  @Autowired
  RequestService requestService;

 
  /**
   * show the requisition-page.
   * 
   * @return requisition.html
   */
  @GetMapping(value = "/requisition")
  public String showContribution() {
    return "requisition";
  }
  
  /**
   * show the new-request form.
   * 
   * @return new-request.html
   */
  @GetMapping(value = "/new-request")
  public String showNewRequestForm(final RequestForm requestForm) {
    return "new-request";
  }
  
  /**
   * Process the logic if a user opens a new request.
   * @param requestForm Bean backed form
   * @param bindingResult Bindingresult for handling errors
   * @param action Action
   * @param request http-request
   * @return
   */
  @PostMapping(value = "/new-request")
  public String processForm(final RequestForm requestForm, /* request form bean */
      final BindingResult bindingResult, /* result to handle or process errors */
      final @RequestParam(value = "action", required = true) String action,
      /* additional parameter because I have also a cancel button in my form */
      final WebRequest request) {
    
    /**Try to validate request title.**/
    String requestTitleValidatorStatus = 
        requestValidator.validateRequestTitle(requestForm.getTitle());
    if (requestTitleValidatorStatus != null) {
      bindingResult.rejectValue("title", requestTitleValidatorStatus);
      return "new-request";
    } else {
      /**Try to validate request text.**/
      String requestTextValidatorStatus = 
          requestValidator.validateRequestText(requestForm.getText());
      if (requestTextValidatorStatus != null) {
        bindingResult.rejectValue("text", requestTextValidatorStatus);
        return "new-request";
      } else {
        requestService.createNewRequest(requestForm);
        return "requisition";
      }
    }    
  }
}

