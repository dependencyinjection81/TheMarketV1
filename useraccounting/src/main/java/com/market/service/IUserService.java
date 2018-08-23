package com.market.service;

import com.market.beans.UserForm;
import com.market.entities.UserEntity;

public interface IUserService {
  
  int registerNewUserAccount(final UserForm userForm);
  
  UserEntity findByUsername(final String userName);
  
  UserEntity findByEmail(final String email);
  
  UserEntity findByConfirmationToken(final String confirmationToken);
  

}
