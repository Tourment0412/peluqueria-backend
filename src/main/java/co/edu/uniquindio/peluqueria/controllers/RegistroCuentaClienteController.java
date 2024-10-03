package co.edu.uniquindio.peluqueria.controllers;

import co.edu.uniquindio.peluqueria.model.documents.Account;
import co.edu.uniquindio.peluqueria.model.enums.AccountType;
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

    @PostMapping("/exist")
    public ResponseEntity<Boolean> existClientAccount(@RequestBody Account account) {
        return ResponseEntity.ok(accountService.existAccountNameEmail(account.getName(),account.getEmail()));
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createClientAccount(@RequestBody Account account) {
        account.setAccountType(AccountType.CLIENT);
        accountService.saveAccount(account);
        return ResponseEntity.noContent().build();
    }

}
