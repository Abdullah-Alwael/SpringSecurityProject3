package com.spring.boot.springsecurityproject3.Repository;

import com.spring.boot.springsecurityproject3.Model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    Account findAccountById(Integer id);

}
