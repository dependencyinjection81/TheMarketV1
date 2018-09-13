package com.market.service;

import com.market.beans.UserForm;
import com.market.entities.User;

public interface IUserService {
  
  int registerNewUserAccount(final UserForm userForm);
  
  User findByUsername(final String userName);
  
  User findByEmail(final String email);
  
  User findByConfirmationToken(final String confirmationToken);
  
}
