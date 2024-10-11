package co.edu.uniquindio.peluqueria.controllers;

import co.edu.uniquindio.peluqueria.dtos.accountdto.CreateAccountDTO;
import co.edu.uniquindio.peluqueria.dtos.accountdto.UpdateAccountDTO;
import co.edu.uniquindio.peluqueria.model.documents.Account;
import co.edu.uniquindio.peluqueria.model.enums.AccountType;
import co.edu.uniquindio.peluqueria.services.interfaces.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@Service
@RestController
@CrossOrigin(origins = "http://localhost:63342")
@RequiredArgsConstructor
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;


    // Endpoint para añadir una nueva cuenta (de empleado o cliente)
    @PostMapping("/create-account")
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

    @GetMapping("/info-account")
    public ResponseEntity<String> getAccount() throws Exception {

    }


}
