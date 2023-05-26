package tw.niq.example.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import tw.niq.example.dto.AccountDto;
import tw.niq.example.entity.AccountEntity;
import tw.niq.example.repository.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService {
	
	private final AccountRepository accountRepository;

	public AccountServiceImpl(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	@Override
	public AccountDto createAccount(AccountDto accountDtoToCreate) {
		
		ModelMapper modelMapper = new ModelMapper();
		
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		AccountEntity accountEntityToSave = modelMapper.map(accountDtoToCreate, AccountEntity.class);
		
		AccountEntity accountEntitySaved = accountRepository.save(accountEntityToSave);
		
		AccountDto accountDtoCreated = modelMapper.map(accountEntitySaved, AccountDto.class);
		
		return accountDtoCreated;
	}

	@Override
	public AccountDto getAccountUserByUserId(String userId) {
		
		ModelMapper modelMapper = new ModelMapper();
		
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		AccountEntity accountEntityFound = accountRepository.findByUserId(userId)
				.orElseThrow(() -> new RuntimeException(userId));
		
		AccountDto accountDtoFound = modelMapper.map(accountEntityFound, AccountDto.class);
		
		return accountDtoFound;
	}

}
