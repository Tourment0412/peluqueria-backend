package co.edu.uniquindio.peluqueria.model.documents;

import co.edu.uniquindio.peluqueria.model.enums.AccountType;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("accounts")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Account {

    @Id
    @EqualsAndHashCode.Include
    private String id;

    private String dni;
    private String name;
    private String email;
    private String password;
    private String address;
    private String phone;
    private int loyaltyPoints;
    private AccountType accountType;

    @Builder
    public Account (String name, String email, String password, String address, String phone, int loyaltyPoints, AccountType accountType) {
        this.name=name;
        this.email=email;
        this.address=address;
        this.phone=phone;
        this.loyaltyPoints=loyaltyPoints;
        this.password=password;
        this.accountType=accountType;
    }

}

