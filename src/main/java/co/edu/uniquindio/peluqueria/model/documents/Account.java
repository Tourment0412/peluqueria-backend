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
    private String name;
    private String email;
    private String password;
    private AccountType accountType;

    @Builder
    public Account (String id,String name, String email, String password, AccountType accountType) {
        this.id = id;
        this.name=name;
        this.email=email;
        this.password=password;
        this.accountType=accountType;
    }

}

