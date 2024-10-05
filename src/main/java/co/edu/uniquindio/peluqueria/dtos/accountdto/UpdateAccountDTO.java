package co.edu.uniquindio.peluqueria.dtos.accountdto;

import co.edu.uniquindio.peluqueria.model.enums.AccountType;

public record UpdateAccountDTO(
        String idAccount,
        String name,
        String dni,
        String address,
        String phone,
        String password,
        String email
) {
}
