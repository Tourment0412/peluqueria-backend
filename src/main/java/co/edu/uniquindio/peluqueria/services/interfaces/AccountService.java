package co.edu.uniquindio.peluqueria.services.interfaces;

import co.edu.uniquindio.peluqueria.dtos.accountdto.CreateAccountDTO;
import co.edu.uniquindio.peluqueria.dtos.accountdto.UpdateAccountDTO;

public interface AccountService {
    public String createAccount(CreateAccountDTO createAccountDTO) throws Exception;
    public String updateAccount(UpdateAccountDTO updateAccountDTO) throws Exception;
    public String deleteAccount(String idAccount) throws Exception;

}
