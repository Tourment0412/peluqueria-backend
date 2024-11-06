package co.edu.uniquindio.peluqueria.controllers;


import co.edu.uniquindio.peluqueria.dto.messages.MessageDTO;
import co.edu.uniquindio.peluqueria.dtos.productdto.ProductFilterDTO;
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

    @PostMapping("/filter-products")
    public ResponseEntity<MessageDTO<List<ProductItemDTO>>> filterProducts(@RequestBody ProductFilterDTO productFilterDTO){
        try {
            List<ProductItemDTO> listFiltered= productService.filterProducts(productFilterDTO);
            return ResponseEntity.ok(new MessageDTO<>(false, listFiltered));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageDTO<>(true, null));
        }
    }


    @GetMapping("/get-all")
    public ResponseEntity<MessageDTO<List<ProductItemDTO>>> getAllProducts() {
        try {
            List<ProductItemDTO> products = productService.getAllProducts();
            System.out.println(products.size());
            return ResponseEntity.ok(new MessageDTO<>(false, products));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new MessageDTO<>(true,null));
        }
    }

    @PutMapping("/update")
    public ResponseEntity<MessageDTO<String>> updateProduct(@RequestBody ProductItemDTO productItemDTO){
        try {
            String productId = productService.updateProduct(productItemDTO);
            return ResponseEntity.ok(new MessageDTO<>(false, productId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageDTO<>(true,e.getMessage()));
        }

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<MessageDTO<String>> deleteProduct(@PathVariable String id){
        try {
            String message = productService.deleteProduct(id);
            return ResponseEntity.ok(new MessageDTO<>(false, message));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageDTO<>(true,e.getMessage()));
        }
    }

}
