package com.market.controller;

import com.market.entities.UserEntity;
import com.market.repositories.UserRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class IndexController {
  
  /**
   * Model.
   */
  @Autowired
  private UserRepository userRepo;

  /**
   * View.
   * 
   * @return the view
   */
  @RequestMapping("/index")
  public String index(ModelMap model) {

    List<UserEntity> users = userRepo.findAll();
    model.put("theUsers", users);

    return "index";
  }

}
