package com.ivoronline.springboot_security_solution_authentication.utility;

import com.ivoronline.springboot_security_solution_authentication.entity.Account;
import com.ivoronline.springboot_security_solution_authentication.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class AccountLoader implements CommandLineRunner {

  //PROPERTIES
  @Autowired AccountRepository accountRepository;
  @Autowired PasswordEncoder passwordEncoder;

  //=======================================================================
  // RUN
  //=======================================================================
  @Override
  @Transactional
  public void run(String... args) throws Exception {

    //CREATE ACCOUNT
    Account account          = new Account();
            account.username = "myuser";
            account.password = passwordEncoder.encode("myuserpassword");
            account.role     = "USER";

    //STORE ACCOUNT INTO DB
    accountRepository.save(account);

  }

}
