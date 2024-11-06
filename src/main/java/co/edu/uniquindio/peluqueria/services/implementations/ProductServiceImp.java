package co.edu.uniquindio.peluqueria.services.implementations;

import co.edu.uniquindio.peluqueria.dtos.productdto.ProductFilterDTO;
import co.edu.uniquindio.peluqueria.dtos.productdto.ProductItemDTO;
import co.edu.uniquindio.peluqueria.dtos.productdto.RegisterProductDTO;
import co.edu.uniquindio.peluqueria.model.documents.Product;
import co.edu.uniquindio.peluqueria.repositories.ProductRepository;
import co.edu.uniquindio.peluqueria.services.interfaces.ProductService;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackFor = Exception.class)
public class ProductServiceImp implements ProductService {

    private final ProductRepository productRepository;
    private final MongoTemplate mongoTemplate;

    public ProductServiceImp(ProductRepository productRepository, MongoTemplate mongoTemplate) {
        this.productRepository = productRepository;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public String registerProduct(RegisterProductDTO registerProductDTO) throws Exception {

        if(registerProductDTO.productName()==null || registerProductDTO.quantity()==0 || registerProductDTO.unitPrice()==0.0){
            throw new Exception("Some values are null or empty");
        }

        //TODO I should use an Account Service to get the Type of account (I just can do this if its admin)

        if(exitsProduct(registerProductDTO.productName())){
            throw new Exception("Product already exists");
        }
        Product product = new Product();
        product.setName(registerProductDTO.productName());
        product.setQuantity(registerProductDTO.quantity());
        product.setUnitPrice(registerProductDTO.unitPrice());
        return productRepository.save(product).getId();
    }



    @Override
    public List<ProductItemDTO> getAllProducts() throws Exception {
        //This could change if we do the treatment with the DTO's (I have to do a map with a stream)
        List<Product> products = productRepository.findAll();
        return products.stream().map(e -> new ProductItemDTO(
                e.getId(),
                e.getName(),
                e.getQuantity(),
                e.getUnitPrice()
        )).collect(Collectors.toList());
    }

    @Override
    public String updateProduct(ProductItemDTO productItemDTO) throws Exception {
        Optional<Product> productReceived= productRepository.findById(productItemDTO.id());
        if(productReceived.isEmpty()){
            throw new Exception("Product not found");
        }
        Product product = productReceived.get();
        product.setName(productItemDTO.productName());
        product.setQuantity(productItemDTO.quantity());
        product.setUnitPrice(productItemDTO.unitPrice());
        return productRepository.save(product).getId();
    }

    @Override
    public String deleteProduct(String id) throws Exception {
        Optional<Product> productReceived= productRepository.findById(id);
        if(productReceived.isEmpty()){
            throw new Exception("Product not found");
        }
        productRepository.deleteById(id);

        return "Product deleted successfully";
    }

    @Override
    public List<ProductItemDTO> filterProducts(ProductFilterDTO productFilterDTO) throws Exception {
        Query query = new Query();
        Map<String, Object> params= new HashMap<>();
        if (productFilterDTO.productName()!=null && !productFilterDTO.productName().isEmpty()){
            String regexName= ".*" + productFilterDTO.productName() + ".*";
            query.addCriteria(Criteria.where("name").is(regexName));
        }
        if(productFilterDTO.priceLower()>0.0f && productFilterDTO.priceUpper()>0.0f){
            query.addCriteria(Criteria.where("unitPrice").gte(productFilterDTO.priceLower()).lte(productFilterDTO.priceUpper()));
        }else if(productFilterDTO.priceLower()>0.0f){
            query.addCriteria(Criteria.where("unitPrice").gte(productFilterDTO.priceLower()));
        }else if(productFilterDTO.priceUpper()>0.0f){
            query.addCriteria(Criteria.where("unitPrice").lte(productFilterDTO.priceUpper()));
        }

        // Condición para que quantity sea mayor a cero
        query.addCriteria(Criteria.where("quantity").gte(0));

        return mongoTemplate.find(query, Product.class).stream().map(e -> new ProductItemDTO(
                e.getId(),
                e.getName(),
                e.getQuantity(),
                e.getUnitPrice()
        )).collect(Collectors.toList());

    }

    @Override
    public Product findProductById(String id) throws Exception {
        Optional<Product> productOptional= productRepository.findById(id);
        if (productOptional.isEmpty()){
            throw new Exception("Product not found");
        }
        return productOptional.get();
    }


    private boolean exitsProduct(String productName) throws Exception {
        Optional <Product> product = productRepository.findByName(productName);
        return product.isPresent();
    }

    private Product getProduct(String productName) throws Exception {
        Optional <Product> product = productRepository.findByName(productName);
        if(product.isEmpty()){
            throw new Exception("Product not found");
        }
        return product.get();
    }
}
