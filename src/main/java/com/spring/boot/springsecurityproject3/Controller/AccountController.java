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

    @PostMapping("/add")
    public ResponseEntity<?> addAccount(@Valid @RequestBody Account account){
        accountService.addAccount(account);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Account added successfully"));

    }
    @GetMapping("/list")
    public ResponseEntity<?> getAllAccounts(){
        return ResponseEntity.status(HttpStatus.OK).body(accountService.getAllAccounts());
    }
    @PutMapping("/update/{accountId}")
    public ResponseEntity<?> updateAccount(@PathVariable Integer accountId, @Valid @RequestBody Account account){
        accountService.updateAccount(accountId,account);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Account updated successfully"));
    }
    @DeleteMapping("/delete/{accountId}")
    public ResponseEntity<?> deleteAccount(@PathVariable Integer accountId){
        accountService.deleteAccount(accountId);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Account deleted successfully"));
    }
}
