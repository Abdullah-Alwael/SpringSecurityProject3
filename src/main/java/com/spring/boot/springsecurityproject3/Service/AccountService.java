package com.spring.boot.springsecurityproject3.Service;

import com.spring.boot.springsecurityproject3.Api.ApiException;
import com.spring.boot.springsecurityproject3.Model.Account;
import com.spring.boot.springsecurityproject3.Model.Customer;
import com.spring.boot.springsecurityproject3.Repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final CustomerService customerService;

    public void addAccount(Account account) {
        accountRepository.save(account);
    }

    public List<Account> getAllAccounts() { // for admin only
        return accountRepository.findAll();
    }

    public Account getAccount(Integer accountId) {
        return accountRepository.findAccountById(accountId);
    }

    public void updateAccount(Integer accountId, Account account) {
        Account oldAccount = getAccount(accountId);

        if (oldAccount == null) {
            throw new ApiException("Error, account does not exist");
        }

        oldAccount.setAccountNumber(account.getAccountNumber());
        oldAccount.setBalance(account.getBalance());
        oldAccount.setIsActive(account.getIsActive());

        accountRepository.save(oldAccount);
    }

    public void deleteAccount(Integer accountId) {
        Account oldAccount = getAccount(accountId);

        if (oldAccount == null) {
            throw new ApiException("Error, account does not exist");
        }

        accountRepository.delete(oldAccount);
    }

    public void activateAccount(Integer accountId) {
        Account account = getAccount(accountId);

        if (account == null) {
            throw new ApiException("Error, account does not exist");
        }

        account.setIsActive(true);
        accountRepository.save(account);
    }

    public void blockAccount(Integer accountId) {
        Account account = getAccount(accountId);

        if (account == null) {
            throw new ApiException("Error, account does not exist");
        }

        account.setIsActive(false);
        accountRepository.save(account);
    }

    public Account viewAccountDetails(Integer accountId, Integer customerId) {
        Account account = getAccount(accountId);

        if (account == null) {
            throw new ApiException("Error, account does not exist");
        }

        Customer customer = customerService.getCustomer(customerId);

        if (customer == null) {
            throw new ApiException("Error, customer does not exist");
        }

        if (!account.getCustomer().getId().equals(customer.getId())) {
            throw new ApiException("Error, action unauthorized");
        }

        return account;

    }

    public List<Account> listMyAccounts(Integer customerId) {
        Customer customer = customerService.getCustomer(customerId);

        if (customer == null) {
            throw new ApiException("Error, customer does not exist");
        }

        return accountRepository.findAccountsByCustomer(customer);
    }

    // deposit or withdraw transaction
    public void atmTransaction(Integer accountId, Integer customerId, double amount) {
        Account account = getAccount(accountId);

        if (account == null) {
            throw new ApiException("Error, account does not exist");
        }

        Customer customer = customerService.getCustomer(customerId);

        if (customer == null) {
            throw new ApiException("Error, customer does not exist");
        }

        if (!account.getCustomer().getId().equals(customer.getId())) {
            throw new ApiException("Error, action unauthorized");
        }

        if (!account.getIsActive()) {
            throw new ApiException("Error, account is not active");
        }

        if (account.getBalance() + amount >= 0) {
            account.setBalance(account.getBalance() + amount); // deposited or withdrawn

            accountRepository.save(account); // transaction complete
        } else {
            throw new ApiException("Error, not enough balance");
        }
    }

    public void betweenAccountsTransaction(Integer fromAccountId,
                                           Integer customerId,
                                           Integer toAccountId,
                                           double amount) {
        Account fromAccount = getAccount(fromAccountId);

        if (fromAccount == null) {
            throw new ApiException("Error, fromAccount does not exist");
        }

        Customer fromCustomer = customerService.getCustomer(customerId);

        if (fromCustomer == null) {
            throw new ApiException("Error, customer does not exist");
        }

        if (!fromAccount.getCustomer().getId().equals(fromCustomer.getId())) {
            throw new ApiException("Error, action unauthorized");
        }

        if (!fromAccount.getIsActive()) {
            throw new ApiException("Error, fromAccount is not active");
        }

        if (amount <= 0) {
            throw new ApiException("Error, can not transfer negative amount or zero");
        }

        if (fromAccount.getBalance() - amount >= 0) {
            // transaction is possible
            Account toAccount = getAccount(toAccountId);

            if (toAccount == null) {
                throw new ApiException("Error, toAccount does not exist");
            }

            if (!toAccount.getIsActive()) {
                throw new ApiException("Error, toAccount is not active");
            }

            toAccount.setBalance(toAccount.getBalance() + amount);
            fromAccount.setBalance(fromAccount.getBalance() - amount);

            accountRepository.save(toAccount);
            accountRepository.save(fromAccount);
            // transaction complete
        } else {
            throw new ApiException("Error, not enough balance");
        }
    }

}
