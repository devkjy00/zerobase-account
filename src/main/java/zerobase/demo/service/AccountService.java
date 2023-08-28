package zerobase.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zerobase.demo.domain.Account;
import zerobase.demo.domain.AccountStatus;
import zerobase.demo.repository.AccountRepository;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    @Transactional
    public void createAccount(){
        Account account = Account.builder()
                .accountNumber("40000")
                .accountStatus(AccountStatus.UNREGISTERED)
                .build();
       accountRepository.save(account);
    }

    @Transactional
    public Account getAccount(Long id){
        return accountRepository.findById(id)
            .orElseThrow(RuntimeException::new);
    }
}
