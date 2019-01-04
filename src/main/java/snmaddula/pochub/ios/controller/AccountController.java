package snmaddula.pochub.ios.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import snmaddula.pochub.ios.config.service.AccountService;
import snmaddula.pochub.ios.domain.Account;

@RestController
@AllArgsConstructor
public class AccountController {

	private AccountService accountService;
	
	@PostMapping
	public ResponseEntity<String> create(@RequestBody Account account) {
		if(accountService.create(account)) {
			return ResponseEntity.ok("Account created!");
		}else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Account creation failed!");
		}
		
	}
}
