package tw.niq.example.controller;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import tw.niq.example.dto.AccountDto;
import tw.niq.example.model.AccountModel;
import tw.niq.example.model.CreateAccountModel;
import tw.niq.example.service.AccountService;

@Slf4j
@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {
	
	private final AccountService accountService;
	
	private final Environment environment;

	public AccountController(AccountService accountService, Environment environment) {
		this.accountService = accountService;
		this.environment = environment;
	}

	@GetMapping("/status")
	public String status() {
		return "Working on port " + environment.getProperty("local.server.port");
	}
	
	@PostMapping(
			consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, 
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<AccountModel> createUser(@Valid @RequestBody CreateAccountModel createAccountModel) {
		
		log.debug(createAccountModel.toString());
		
		ModelMapper modelMapper = new ModelMapper();
		
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		AccountDto accountDtoToCreate = modelMapper.map(createAccountModel, AccountDto.class);
		
		AccountDto accountDtoCreated = accountService.createAccount(accountDtoToCreate);
		
		AccountModel accountModel = modelMapper.map(accountDtoCreated, AccountModel.class);
		
		return new ResponseEntity<AccountModel>(accountModel, HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/{userId}", 
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<AccountModel> getAccount(@PathVariable("userId") String userId) {
		
		log.debug(userId);
		
		ModelMapper modelMapper = new ModelMapper();
		
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		AccountDto accountDtoFound = accountService.getAccountUserByUserId(userId);
		
		AccountModel accountModel = modelMapper.map(accountDtoFound, AccountModel.class);
		
		return new ResponseEntity<AccountModel>(accountModel, HttpStatus.OK);
	}
	
}
