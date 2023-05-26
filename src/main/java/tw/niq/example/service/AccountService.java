package tw.niq.example.service;

import tw.niq.example.dto.AccountDto;

public interface AccountService {

	AccountDto createAccount(AccountDto accountDto);

	AccountDto getAccountUserByUserId(String userId);

}
