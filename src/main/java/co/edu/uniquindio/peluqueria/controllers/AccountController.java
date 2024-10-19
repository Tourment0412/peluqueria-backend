package co.edu.uniquindio.peluqueria.controllers;

import co.edu.uniquindio.peluqueria.dtos.accountdto.CreateAccountDTO;
import co.edu.uniquindio.peluqueria.dtos.accountdto.InfoLoginAccount;
import co.edu.uniquindio.peluqueria.dtos.accountdto.LoginAccountDTO;
import co.edu.uniquindio.peluqueria.dtos.accountdto.UpdateAccountDTO;
import co.edu.uniquindio.peluqueria.services.interfaces.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Service
@RestController
@CrossOrigin(origins = "http://localhost:63342")
@RequiredArgsConstructor
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;


    @PostMapping("/login")
    public ResponseEntity<InfoLoginAccount> login(@RequestBody LoginAccountDTO account) {
        return ResponseEntity.ok(accountService.findAccountLogin(account.email(),account.password()));
    }

    @PostMapping("/exist")
    public ResponseEntity<Boolean> existClientAccount(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        return ResponseEntity.ok(accountService.existAccountByEmail(email));
    }

    // Endpoint para añadir una nueva cuenta (de empleado o cliente)
    @PostMapping("/create")
    public ResponseEntity<String> createAccount(@RequestBody CreateAccountDTO createAccountDTO) throws Exception {
        String message = accountService.createAccount(createAccountDTO);
        return ResponseEntity.ok(message);
    }

    @DeleteMapping("/delete-appointment/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable String id) throws Exception {
        String message = accountService.deleteAccount(id);
        return ResponseEntity.ok(message);
    }

    @PutMapping("/update-account")
    public ResponseEntity<String> updateAccount(@Valid @RequestBody UpdateAccountDTO account) throws Exception {
        String message = accountService.updateAccount(account);
        return ResponseEntity.ok(message);
    }

    /*
    @GetMapping("/info-account")
    public ResponseEntity<String> getAccount() throws Exception {

    }

     */


}
