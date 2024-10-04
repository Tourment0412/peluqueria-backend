package co.edu.uniquindio.peluqueria.controllers;

import co.edu.uniquindio.peluqueria.model.documents.Account;
import co.edu.uniquindio.peluqueria.services.implementations.AccountServiceImp;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    private final AccountServiceImp accountService;

    public LoginController(AccountServiceImp accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<Account> login(@RequestBody Account account) {
        Account account0=accountService.findAccount(account.getEmail(),account.getPassword());
        System.out.println(account0);
        return ResponseEntity.ok(account0);
    }

}
