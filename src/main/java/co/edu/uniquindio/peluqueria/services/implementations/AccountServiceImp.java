package co.edu.uniquindio.peluqueria.services.implementations;

import co.edu.uniquindio.peluqueria.dtos.accountdto.CreateAccountDTO;
import co.edu.uniquindio.peluqueria.dtos.accountdto.InfoAccountDTO;
import co.edu.uniquindio.peluqueria.dtos.accountdto.UpdateAccountDTO;
import co.edu.uniquindio.peluqueria.model.documents.Account;
import co.edu.uniquindio.peluqueria.model.enums.AccountType;
import co.edu.uniquindio.peluqueria.repositories.AccountRepository;
import co.edu.uniquindio.peluqueria.services.interfaces.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImp implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImp(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public boolean existAccountByEmail(String email) {
        return accountRepository.findByEmail(email).isPresent();
    }

    private boolean existAccountByDni(String dni) { return accountRepository.findByDni(dni).isPresent(); }


    @Override
    public String createAccount(CreateAccountDTO createAccountDTO) throws Exception {
        if (existAccountByEmail(createAccountDTO.email())) {
            throw new Exception("Account with this email already exists");
        }

        if (existAccountByDni(createAccountDTO.dni())) {
            throw new Exception("Account with this dni already exists");
        }

        Account account = new Account();
        account.setEmail(createAccountDTO.email());
        account.setDni(createAccountDTO.dni());
        account.setPassword(createAccountDTO.password());
        account.setName(createAccountDTO.name());
        account.setAddress(createAccountDTO.address());
        account.setPhone(createAccountDTO.phone());
        account.setLoyaltyPoints(0);
        account.setAccountType(createAccountDTO.accountType());
        accountRepository.save(account);
        return account.getId();
    }



    @Override
    public String updateAccount(UpdateAccountDTO updateAccountDTO) throws Exception {
        Account account = getAccount(updateAccountDTO.idAccount());

        account.setName(updateAccountDTO.name());
        account.setDni(updateAccountDTO.dni());
        account.setAddress(updateAccountDTO.address());
        account.setPhone(updateAccountDTO.phone());
        account.setPassword(updateAccountDTO.password());
        account.setEmail(updateAccountDTO.email());
        accountRepository.save(account);
        return account.getId();
    }

    @Override
    public String deleteAccount(String idAccount) throws Exception {
        Account account = getAccount(idAccount);
        accountRepository.delete(account);
        return "Account with id: " + idAccount + " deleted";
    }

    @Override
    public InfoAccountDTO getInfoAccount(String idAccount) throws Exception {
        Account account = getAccount(idAccount);
        if (account.getAccountType().equals(AccountType.WORKER) || account.getAccountType().equals(AccountType.ADMIN)) {
            return new InfoAccountDTO(
                    account.getId(),
                    account.getDni(),
                    account.getName(),
                    account.getPassword(),
                    account.getPhone(),
                    account.getAddress(),
                    account.getEmail(),
                    0,
                    account.getAccountType()
            );
        }
        return new InfoAccountDTO(
                account.getId(),
                account.getDni(),
                account.getName(),
                account.getPassword(),
                account.getPhone(),
                account.getAddress(),
                account.getEmail(),
                account.getLoyaltyPoints(),
                account.getAccountType()
        );
    }

    public String addLoyaltyPoints(String idAccount) throws Exception {
        Account account = getAccount(idAccount);
        account.setLoyaltyPoints(account.getLoyaltyPoints() + 1);
        accountRepository.save(account);
        return "Loyalty points added to account: " + account.getId();
    }

    private Account getAccount(String id) throws Exception {
        Optional<Account> accountOptional = accountRepository.findAccountById(id);
        if (accountOptional.isEmpty()) {
            throw new Exception("Account with this id does not exist");
        }

        return accountOptional.get();
    }
}
