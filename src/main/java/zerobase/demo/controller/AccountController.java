package zerobase.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import zerobase.demo.domain.Account;
import zerobase.demo.service.AccountService;
import zerobase.demo.service.RedisTestService;

@RestController
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;
    private final RedisTestService redisTestService;

    @GetMapping("/create-account")
    public String createAccount(){
        accountService.createAccount();
        return "success";
    }

    @GetMapping("/account/{id}")
    public Account getAccount(@PathVariable Long id){
        return accountService.getAccount(id);
    }


    @GetMapping("/get-lock")
    public String getLock(){
        return redisTestService.getLock();
    }
}
