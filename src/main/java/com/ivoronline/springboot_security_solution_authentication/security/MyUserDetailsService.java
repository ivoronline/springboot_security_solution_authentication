package com.ivoronline.springboot_security_solution_authentication.security;

import com.ivoronline.springboot_security_solution_authentication.entity.Account;
import com.ivoronline.springboot_security_solution_authentication.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

  //PROPERTIES
  @Autowired AccountRepository accountRepository;

  //=======================================================================
  // LOAD USER BY USERNAMEDobro
  //=======================================================================
  @Override
  public UserDetails loadUserByUsername(String enteredUsername) throws UsernameNotFoundException {

    //GET HASHED PASSWORD & ROLE (From real DB) -------------------------------------------------------------

    //GET USER/ACCOUNT (From DB)
    Account account = accountRepository.findByUsername(enteredUsername);

    //CHECK IF USER EXISTS
    if (account == null) { throw new UsernameNotFoundException(enteredUsername); }     //Bad credentials

    //GET PASSWORD & ROLE
    String storedPassword = account.password;
    String role           = account.role;

    //RETURN USER WITH PASSWORD & ROLE ----------------------------------------------------------------------

    //CREATE USER
    UserDetails userDetails= User.withUsername(enteredUsername).password(storedPassword).roles(role).build();

    //RETURN USER
    return userDetails;

  }

}


