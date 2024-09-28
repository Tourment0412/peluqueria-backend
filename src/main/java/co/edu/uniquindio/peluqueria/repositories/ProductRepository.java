package co.edu.uniquindio.peluqueria.repositories;

import co.edu.uniquindio.peluqueria.model.documents.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

public interface ProductRepository extends MongoRepository<Product, String> {



}
