package co.edu.uniquindio.peluqueria.dtos.accountdto;

import co.edu.uniquindio.peluqueria.model.enums.AccountType;

public record InfoLoginAccount(
        String email,
        AccountType accountType
) {
}
