package co.edu.uniquindio.peluqueria.controllers;


import co.edu.uniquindio.peluqueria.dto.messages.MessageDTO;
import co.edu.uniquindio.peluqueria.dtos.productdto.ProductItemDTO;
import co.edu.uniquindio.peluqueria.dtos.productdto.RegisterProductDTO;
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
    public ResponseEntity<MessageDTO<String>> registerProduct(@RequestBody RegisterProductDTO registerProductDTO) {
        try {
            String productId= productService.registerProduct(registerProductDTO);
            return ResponseEntity.ok(new MessageDTO<>(false, productId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageDTO<>(true, e.getMessage()));
        }
    }

    @GetMapping("/get-all")
    public ResponseEntity<MessageDTO<List<ProductItemDTO>>> getAllProducts() {
        try {
            List<ProductItemDTO> products = productService.getAllProducts();
            return ResponseEntity.ok(new MessageDTO<>(false, products));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new MessageDTO<>(true,null));
        }
    }

}
