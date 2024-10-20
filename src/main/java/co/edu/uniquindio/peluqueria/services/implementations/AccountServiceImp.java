package co.edu.uniquindio.peluqueria.services.implementations;

import co.edu.uniquindio.peluqueria.dtos.accountdto.*;
import co.edu.uniquindio.peluqueria.model.documents.Account;
import co.edu.uniquindio.peluqueria.model.enums.AccountType;
import co.edu.uniquindio.peluqueria.repositories.AccountRepository;
import co.edu.uniquindio.peluqueria.services.interfaces.AccountService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountServiceImp implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImp(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Boolean existAccountByEmail(String email) {
        return accountRepository.findByEmail(email).isPresent();
    }

    @Override
    public Account getAccountByDni(String dni) {
        Optional<Account> account = accountRepository.findByDni(dni);
        return account.orElse(null);
    }


    @Override
    public String createAccount(CreateAccountDTO createAccountDTO) throws Exception {
        if (existAccountByEmail(createAccountDTO.email())) {
            throw new Exception("Account with this email already exists");
        }

        if (createAccountDTO.dni() != null) {
            getAccountByDni(createAccountDTO.dni());
            throw new Exception("Account with this dni already exists");
        }

        Account account = getAccount(createAccountDTO);
        accountRepository.save(account);
        return account.getId();
    }

    private static Account getAccount(CreateAccountDTO createAccountDTO) {
        Account account = new Account();
        account.setEmail(createAccountDTO.email());
        account.setDni(createAccountDTO.dni());
        account.setPassword(createAccountDTO.password());
        account.setName(createAccountDTO.name());
        account.setAddress(createAccountDTO.address());
        account.setPhone(createAccountDTO.phone());
        account.setLoyaltyPoints(0);

        if (createAccountDTO.accountType()==null) {
            account.setAccountType(AccountType.CLIENT);
        } else {
            account.setAccountType(createAccountDTO.accountType());
        }
        return account;
    }


    @Override
    public String updateAccount(UpdateAccountDTO updateAccountDTO) throws Exception {
        Account account = getAccountById(updateAccountDTO.idAccount());

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
        Account account = getAccountById(idAccount);
        accountRepository.delete(account);
        return "Account with id: " + idAccount + " deleted";
    }

    @Override
    public InfoAccountDTO getInfoAccount(String idAccount) throws Exception {
        Account account = getAccountById(idAccount);
        if (account.getAccountType().equals(AccountType.EMPLOYEE) || account.getAccountType().equals(AccountType.ADMIN)) {
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

    @Override
    public InfoLoginAccount findAccountLogin(String email, String password) {
        Optional<Account> accountObtained=accountRepository.findByEmailAndPassword(email,password);
        if (accountObtained.isPresent()) {
            Account account=accountObtained.get();
            return new InfoLoginAccount(account.getEmail(),account.getAccountType());
        } else {
            return null;
        }
    }

    public String addLoyaltyPoints(String idAccount) throws Exception {
        Account account = getAccountById(idAccount);
        account.setLoyaltyPoints(account.getLoyaltyPoints() + 1);
        accountRepository.save(account);
        return "Loyalty points added to account: " + account.getId();
    }

    private Account getAccountById(String id) throws Exception {
        Optional<Account> accountOptional = accountRepository.findAccountById(id);
        if (accountOptional.isEmpty()) {
            throw new Exception("Account with this id does not exist");
        }

        return accountOptional.get();
    }

    @Override
    public List<AccountItemDTO> filterAccounts(String search) {
        List<Account> accountsFiltered;

        // Si el campo de búsqueda está vacío, obtener todas las cuentas de tipo CLIENT
        if (search == null || search.trim().isEmpty()) {
            accountsFiltered = accountRepository.findAllByAccountType(AccountType.CLIENT);
        } else {
            // Si hay un término de búsqueda, aplicar el filtro sobre nombre, email y teléfono
            accountsFiltered = accountRepository.findAccounts(search);
        }

        return accountsFiltered.stream()
                .map(account -> new AccountItemDTO(
                        account.getName(),
                        account.getEmail(),
                        account.getPhone()
                ))
                .collect(Collectors.toList());
    }
}
