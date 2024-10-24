package co.edu.uniquindio.peluqueria.controllers;

import co.edu.uniquindio.peluqueria.dto.messages.MessageDTO;
import co.edu.uniquindio.peluqueria.dtos.accountdto.*;
import co.edu.uniquindio.peluqueria.services.interfaces.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable String id) throws Exception {
        String message = accountService.deleteAccount(id);
        return ResponseEntity.ok(message);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateAccount(@Valid @RequestBody UpdateAccountDTO account) throws Exception {
        String message = accountService.updateAccount(account);
        return ResponseEntity.ok(message);
    }

    @PostMapping("/filter")
    public ResponseEntity<MessageDTO<List<AccountItemDTO>>> filterEventsClient(@Valid @RequestBody Map<String, String> request){
        String search = request.get("search");
        List<AccountItemDTO> accounts = accountService.filterAccounts(search);
        System.out.println(accounts.toString());
        return ResponseEntity.ok(new MessageDTO<>(false, accounts));
    }

    @PostMapping("/filterEmployee")
    public ResponseEntity<MessageDTO<List<AccountItemDTO>>> filterAccountsEmployee(@Valid @RequestBody Map<String, String> request){
        String search = request.get("search");
        List<AccountItemDTO> accounts = accountService.filterAccountsEmployee(search);
        System.out.println(accounts.toString());
        return ResponseEntity.ok(new MessageDTO<>(false, accounts));
    }






}
