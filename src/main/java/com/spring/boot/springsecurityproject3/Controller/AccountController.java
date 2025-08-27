package com.spring.boot.springsecurityproject3.Controller;

import com.spring.boot.springsecurityproject3.Api.ApiResponse;
import com.spring.boot.springsecurityproject3.Model.Account;
import com.spring.boot.springsecurityproject3.Service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    // customer
    @PostMapping("/create")
    public ResponseEntity<?> addAccount(@Valid @RequestBody Account account){
        accountService.addAccount(account);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Account added successfully"));

    }

    // admin
    @GetMapping("/list")
    public ResponseEntity<?> getAllAccounts(){
        return ResponseEntity.status(HttpStatus.OK).body(accountService.getAllAccounts());
    }

    // Employee
    @PutMapping("/update/{accountId}")
    public ResponseEntity<?> updateAccount(@PathVariable Integer accountId, @Valid @RequestBody Account account){
        accountService.updateAccount(accountId,account);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Account updated successfully"));
    }

    // customer
    @DeleteMapping("/delete/{accountId}")
    public ResponseEntity<?> deleteAccount(@PathVariable Integer accountId){
        accountService.deleteAccount(accountId);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Account deleted successfully"));
    }

    // Employee
    @PutMapping("/activate/{accountId}")
    public ResponseEntity<?> activateAccount(@PathVariable Integer accountId){
        accountService.activateAccount(accountId);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Account activated successfully"));
    }

    // Employee/Customer
    @GetMapping("/details/{accountId}/{customerId}")
    public ResponseEntity<?> viewAccountDetails(@PathVariable Integer accountId,
                                                @PathVariable Integer customerId){
        return ResponseEntity.status(HttpStatus.OK).body(accountService.viewAccountDetails(accountId,customerId));

    }

    // Customer
    @GetMapping("/my-accounts/{customerId}")
    public ResponseEntity<?> listMyAccounts(@PathVariable Integer customerId){
        return ResponseEntity.status(HttpStatus.OK).body(accountService.listMyAccounts(customerId));
    }

    // Customer, deposit or withdraw based on the amount sign positive or negative
    @PutMapping("/deposit-withdraw/{accountId}/{customerId}/{amount}")
    public ResponseEntity<?> atmTransaction(@PathVariable Integer accountId,
                                            @PathVariable Integer customerId,
                                            @PathVariable double amount){

        accountService.atmTransaction(accountId, customerId, amount);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Account activated successfully"));
    }

    // customer
    @PutMapping("/transfer/{fromAccountId}/{customerId}/{toAccountId}/{amount}")
    public ResponseEntity<?> betweenAccountsTransaction(@PathVariable Integer fromAccountId,
                                                        @PathVariable Integer customerId,
                                                        @PathVariable Integer toAccountId,
                                                        @PathVariable double amount) {

        accountService.betweenAccountsTransaction(fromAccountId, customerId, toAccountId, amount);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Account activated successfully"));
    }

    // Employee
    @PutMapping("/block/{accountId}")
    public ResponseEntity<?> blockAccount(@PathVariable Integer accountId){
        accountService.blockAccount(accountId);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Account blocked successfully"));

    }
}
