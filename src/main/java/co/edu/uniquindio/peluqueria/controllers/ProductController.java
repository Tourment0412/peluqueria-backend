package co.edu.uniquindio.peluqueria.controllers;


import co.edu.uniquindio.peluqueria.dto.productdto.ProductItemDTO;
import co.edu.uniquindio.peluqueria.dto.productdto.RegisterProductDTO;
import co.edu.uniquindio.peluqueria.model.documents.Product;
import co.edu.uniquindio.peluqueria.services.interfaces.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerProduct(@RequestBody RegisterProductDTO registerProductDTO) {
        try {
            String productId= productService.registerProduct(registerProductDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(productId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllProducts() {
        try {
            List<ProductItemDTO> products = productService.getAllProducts();
            return ResponseEntity.ok(products);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
