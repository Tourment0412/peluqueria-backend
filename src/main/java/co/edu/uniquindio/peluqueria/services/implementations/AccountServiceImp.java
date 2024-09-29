package co.edu.uniquindio.peluqueria.services.implementations;

import co.edu.uniquindio.peluqueria.model.documents.Account;
import co.edu.uniquindio.peluqueria.repositories.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class AccountServiceImp {

    private final AccountRepository accountRepository;

    public AccountServiceImp(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public boolean existAccount(String name, String email) {
        Account account=accountRepository.findByNameAndEmail(name, email);
        return account != null;
    }

    public Account findAccount(String email, String password) {
        return accountRepository.findByNameAndPassword(email, password);
    }

    public void saveAccount(Account account) {
        accountRepository.save(account);
    }
}
