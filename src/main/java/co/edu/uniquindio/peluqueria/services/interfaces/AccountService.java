package co.edu.uniquindio.peluqueria.services.interfaces;

import co.edu.uniquindio.peluqueria.dtos.accountdto.*;
import co.edu.uniquindio.peluqueria.model.documents.Account;

import java.util.List;

public interface AccountService {
    String createAccount(CreateAccountDTO createAccountDTO) throws Exception;
    String updateAccount(UpdateAccountDTO updateAccountDTO) throws Exception;
    String deleteAccount(String idAccount) throws Exception;
    InfoAccountDTO getInfoAccount(String idAccount) throws Exception;
    Account getAccountByDni(String dni);
    InfoLoginAccount findAccountLogin(String email, String password);
    List<AccountItemDTO> filterAccounts(String search);
    Boolean existAccountByEmail(String email);
    List<AccountItemDTO> filterAccountsEmployee(String search);
}
