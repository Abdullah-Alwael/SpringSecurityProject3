package com.spring.boot.springsecurityproject3.Service;

import com.spring.boot.springsecurityproject3.Api.ApiException;
import com.spring.boot.springsecurityproject3.Model.Account;
import com.spring.boot.springsecurityproject3.Repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    public void addAccount(Account account){
        accountRepository.save(account);
    }

    public List<Account> getAllAccounts(){ // for admin only
        return accountRepository.findAll();
    }

    public Account getAccount(Integer accountId){
        return accountRepository.findAccountById(accountId);
    }

    public void updateAccount(Integer accountId, Account account){
        Account oldAccount = getAccount(accountId);

        if(oldAccount == null){
            throw new ApiException("Error, account does not exist");
        }

        oldAccount.setAccountNumber(account.getAccountNumber());
        oldAccount.setBalance(account.getBalance());
        oldAccount.setIsActive(account.getIsActive());

        accountRepository.save(oldAccount);
    }

    public void deleteAccount(Integer accountId){
        Account oldAccount = getAccount(accountId);

        if(oldAccount == null){
            throw new ApiException("Error, account does not exist");
        }

        accountRepository.delete(oldAccount);
    }
}
