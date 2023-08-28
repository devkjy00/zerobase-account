package zerobase.demo.controller;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import zerobase.demo.domain.Account;
import zerobase.demo.domain.AccountStatus;
import zerobase.demo.service.AccountService;
import zerobase.demo.service.RedisTestService;

@WebMvcTest(AccountController.class)
class AccountControllerTest {
    @MockBean
    private AccountService accountService;

    @MockBean
    private RedisTestService redisTestService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void successGetAccount () throws Exception {
        // given
        given(accountService.getAccount(anyLong()))
            .willReturn(Account.builder()
                .accountNumber("40000")
                .accountStatus(AccountStatus.UNREGISTERED)
                .build());

        // when
        // then

        mockMvc.perform(get("/account/1"))
            .andDo(print())
            // $. 는 시작점을 의미
            .andExpect(jsonPath("$.accountNumber").value("40000"))
            .andExpect(jsonPath("$.accountStatus").value("UNREGISTERED"))
            .andExpect(status().isOk());


    }



}