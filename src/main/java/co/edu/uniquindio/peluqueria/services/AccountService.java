package co.edu.uniquindio.peluqueria.services;

import co.edu.uniquindio.peluqueria.model.documents.Account;
import co.edu.uniquindio.peluqueria.repositories.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public boolean existAccount(String name, String email) {
        Account account=accountRepository.findByNameAndEmail(name, email);
        if(account==null) {
            return false;
        }
        return true;
    }

    public Account findAccount(String email, String password) {
        return accountRepository.findByNameAndPassword(email, password);
    }

    public void saveAccount(Account account) {
        accountRepository.save(account);
    }
}
