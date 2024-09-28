package co.edu.uniquindio.peluqueria.model.documents;

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
    private String id;
    private String name;
    private String email;
    private String password;
    private AccountType accountType;
}
