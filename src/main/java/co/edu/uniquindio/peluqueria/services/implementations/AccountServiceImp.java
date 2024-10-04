package co.edu.uniquindio.peluqueria.services.implementations;

import co.edu.uniquindio.peluqueria.model.documents.Account;
import co.edu.uniquindio.peluqueria.repositories.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImp {

    private final AccountRepository accountRepository;

    public AccountServiceImp(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public boolean existAccountNameEmail(String name, String email) {
        return accountRepository.findByNameOrEmail(name, email)!=null;
    }

    public Account findAccount(String email, String password) {
        return accountRepository.findByEmailAndPassword(email, password);
    }

    public void saveAccount(Account account) {
        accountRepository.save(account);
    }
}
