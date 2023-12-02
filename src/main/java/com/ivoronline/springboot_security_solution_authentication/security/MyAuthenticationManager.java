package com.ivoronline.springboot_security_solution_authentication.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import java.util.Set;

@Component
public class MyAuthenticationManager implements AuthenticationManager {

  //PROPERTIES
  @Autowired UserDetailsService userDetailsService;

  //=================================================================
  // AUTHENTICATE
  //=================================================================
  @Override
  public Authentication authenticate(Authentication inputAuthentication) {

    //GET ENTERED CREDENTIALS
    String enteredUsername = (String) inputAuthentication.getPrincipal();   //Entered Username
    String enteredPassword = (String) inputAuthentication.getCredentials(); //Entered Password

    //GET USER DETAILS              (throws UsernameNotFoundException)
    UserDetails           userDetails    = userDetailsService.loadUserByUsername(enteredUsername);
    String                storedPassword = userDetails.getPassword();
    Set<GrantedAuthority> authorities    = (Set<GrantedAuthority>) userDetails.getAuthorities();

    //PREPARE AUTHENTICATION OBJECT (setAuthenticated(true) is called during Constructor call)
    Authentication outputAuthentication = new UsernamePasswordAuthenticationToken(enteredUsername, null, authorities);
    if (!enteredPassword.equals(storedPassword)) { outputAuthentication.setAuthenticated(false); }

    //RETURN AUTHENTICATION
    return outputAuthentication;

  }

}

