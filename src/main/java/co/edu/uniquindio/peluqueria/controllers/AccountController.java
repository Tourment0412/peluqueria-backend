package co.edu.uniquindio.peluqueria.controllers;

import co.edu.uniquindio.peluqueria.model.documents.Account;
import co.edu.uniquindio.peluqueria.model.enums.AccountType;
import co.edu.uniquindio.peluqueria.services.implementations.AccountServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/account")
public class AccountController {

    private final AccountServiceImp accountService;

    //TODO NO HAY EXCEPCIONES TOCA QUE ESAS COSAS TAMBIEN SE MANDEN COMO INFORMACION.

    @PostMapping("/login")
    public ResponseEntity<Account> login(@RequestBody Account account) {
        return ResponseEntity.ok(accountService.findAccount(account.getEmail(),account.getPassword()));
    }

    //TODO Esto deberia ser llamado por el de crear no deberia ser controlador
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
