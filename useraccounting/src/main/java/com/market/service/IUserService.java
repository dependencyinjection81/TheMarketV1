package com.market.service;

import com.market.beans.UserForm;
import com.market.entities.UserEntity;

public interface IUserService {
  
  UserEntity registerNewUserAccount(final UserForm userForm);
  
  UserEntity findByEmail(final String email);
  
  UserEntity findByConfirmationToken(final String confirmationToken);
  

}
