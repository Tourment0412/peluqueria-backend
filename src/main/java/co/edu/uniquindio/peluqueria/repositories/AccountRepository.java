package co.edu.uniquindio.peluqueria.repositories;

import co.edu.uniquindio.peluqueria.model.documents.Account;
import co.edu.uniquindio.peluqueria.model.enums.AccountType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends MongoRepository<Account, String> {

    @Query("{email: ?0,password: ?1}")
    Optional<Account> findByEmailAndPassword(String email, String password);

    @Query("{ $or: [ { 'name': ?0 }, { 'email': ?1 } ] }")
    Account findByNameOrEmail(String name,String email);

    @Query("{'email': ?0}")
    Optional<Account> findByEmail(String email);

    @Query("{'dni': ?0}")
    Optional<Account> findByDni(String dni);

    @Query("{'id': ?0}")
    Optional<Account> findAccountById(String id);

    @Query("{ $and: [ { 'accountType': 'CLIENT' }, { $or: [ " +
            "{ 'name': { $regex: ?0, $options: 'i' } }, " +
            "{ 'phone': { $regex: ?0, $options: 'i' } }, " +
            "{ 'email': { $regex: ?0, $options: 'i' } } ] } ] }")
    List<Account> findAccounts(String search);

    @Query("{ $and: [ { 'accountType': 'EMPLOYEE' }, { $or: [ " +
            "{ 'name': { $regex: ?0, $options: 'i' } }, " +
            "{ 'phone': { $regex: ?0, $options: 'i' } }, " +
            "{ 'email': { $regex: ?0, $options: 'i' } } ] } ] }")
    List<Account> findAccountsEmployee(String search);


    @Query("{'accountType': ?0}")
    List<Account> findAllByAccountType(AccountType accountType);
}
