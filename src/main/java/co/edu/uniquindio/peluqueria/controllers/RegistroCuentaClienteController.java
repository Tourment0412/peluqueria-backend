package co.edu.uniquindio.peluqueria.controllers;

import co.edu.uniquindio.peluqueria.model.documents.Account;
import co.edu.uniquindio.peluqueria.services.implementations.AccountServiceImp;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/RegistroCuentaCliente")
public class RegistroCuentaClienteController {

    private final AccountServiceImp accountService;

    public RegistroCuentaClienteController(AccountServiceImp accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<Boolean> existClientAccount(@RequestBody String name, @RequestBody String email) {
        return ResponseEntity.ok(accountService.existAccount(name,email));
    }

    @PostMapping
    public ResponseEntity<Void> createClientAccount(Account account) {
        accountService.saveAccount(account);
        return ResponseEntity.noContent().build();
    }

}
