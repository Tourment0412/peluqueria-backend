package co.edu.uniquindio.peluqueria.repositories;

import co.edu.uniquindio.peluqueria.model.documents.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends MongoRepository<Account, String> {

    @Query("{name: ?0,password: ?1}")
    Account findByNameAndPassword(String name, String password);

    @Query("{ $or: [ { 'name': ?0 }, { 'email': ?1 } ] }")
    Account findByNameOrEmail(String name,String email);

    @Query("{'email': ?0}")
    Optional<Account> findByEmail(String email);

    @Query("{'dni': ?0}")
    Optional<Account> findByDni(String dni);

    @Query("{'id': ?0}")
    Optional<Account> findAccountById(String id);
}
