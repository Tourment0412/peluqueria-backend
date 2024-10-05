package co.edu.uniquindio.peluqueria.dtos.accountdto;

import co.edu.uniquindio.peluqueria.model.enums.AccountType;

public record InfoAccountDTO(
        String id,
        String dni,
        String name,
        String password,
        String phone,
        String address,
        String email,
        int loyaltyPoints,
        AccountType accountType

) {
}
