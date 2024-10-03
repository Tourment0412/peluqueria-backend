package co.edu.uniquindio.peluqueria.repositories;

import co.edu.uniquindio.peluqueria.model.documents.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends MongoRepository<Account, String> {

    @Query("{name: ?0,password: ?1}")
    public Account findByNameAndPassword(String name, String password);

    @Query("{ $or: [ { 'name': ?0 }, { 'email': ?1 } ] }")
    public Account findByNameOrEmail(String name,String email);


}
