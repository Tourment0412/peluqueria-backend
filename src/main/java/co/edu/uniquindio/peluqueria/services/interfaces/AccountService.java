package co.edu.uniquindio.peluqueria.services.interfaces;

import co.edu.uniquindio.peluqueria.dtos.accountdto.CreateAccountDTO;
import co.edu.uniquindio.peluqueria.dtos.accountdto.InfoAccountDTO;
import co.edu.uniquindio.peluqueria.dtos.accountdto.UpdateAccountDTO;

public interface AccountService {
    String createAccount(CreateAccountDTO createAccountDTO) throws Exception;
    String updateAccount(UpdateAccountDTO updateAccountDTO) throws Exception;
    String deleteAccount(String idAccount) throws Exception;
    InfoAccountDTO getInfoAccount(String idAccount) throws Exception;
}
