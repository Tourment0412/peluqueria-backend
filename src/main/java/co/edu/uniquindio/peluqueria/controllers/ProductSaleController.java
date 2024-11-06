package co.edu.uniquindio.peluqueria.controllers;


import co.edu.uniquindio.peluqueria.dto.messages.MessageDTO;
import co.edu.uniquindio.peluqueria.dtos.productsaledto.ProductSaleDTO;
import co.edu.uniquindio.peluqueria.services.interfaces.ProductSaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product-sale")
public class ProductSaleController {


    private final ProductSaleService productSaleService;

    @PostMapping("/create-sale")
    public ResponseEntity<MessageDTO<String>> createProductSale (@RequestBody ProductSaleDTO productSaleDTO){
        try {
            String id= productSaleService.createProductSale(productSaleDTO);
            return ResponseEntity.ok(new MessageDTO<>(false, id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageDTO<>(true, e.getMessage()));
        }

    }





}
