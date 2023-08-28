package zerobase.demo.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

import java.util.Optional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import zerobase.demo.domain.Account;
import zerobase.demo.domain.AccountStatus;
import zerobase.demo.repository.AccountRepository;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {
    @Mock
    private AccountRepository accountRepository;


    @InjectMocks
    private AccountService accountService;

    @BeforeEach
    void init() {
        accountService.createAccount();
    }

    @Test
    @DisplayName("계좌 조회 성공")
    void test() {
        // given
        given(accountRepository.findById(anyLong()))
            .willReturn(Optional.of(Account.builder()
                .accountNumber("40000")
                .accountStatus(AccountStatus.UNREGISTERED)
                .build()));

        // when
        Account account = accountService.getAccount(1L);

        // then
        assertEquals("40000", account.getAccountNumber());
        assertEquals(AccountStatus.UNREGISTERED, account.getAccountStatus());
    }


}