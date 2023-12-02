package com.ivoronline.springboot_security_solution_authentication.repository;

import com.ivoronline.springboot_security_solution_authentication.entity.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Integer> {
  Account findByUsername(String Username);
}
