package co.edu.uniquindio.peluqueria.services.implementations;

import co.edu.uniquindio.peluqueria.dtos.productdto.ProductItemDTO;
import co.edu.uniquindio.peluqueria.dtos.productsaledto.ProductSaleDTO;
import co.edu.uniquindio.peluqueria.model.documents.Account;
import co.edu.uniquindio.peluqueria.model.documents.Product;
import co.edu.uniquindio.peluqueria.model.documents.ProductSale;
import co.edu.uniquindio.peluqueria.model.vo.ProductSaleDetail;
import co.edu.uniquindio.peluqueria.repositories.ProductSaleRepository;
import co.edu.uniquindio.peluqueria.services.interfaces.AccountService;
import co.edu.uniquindio.peluqueria.services.interfaces.ProductSaleService;
import co.edu.uniquindio.peluqueria.services.interfaces.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class ProductSaleServiceImp implements ProductSaleService {

    private final ProductSaleRepository productSaleRepository;
    private final AccountService accountService;
    private final ProductService productService;

    public ProductSaleServiceImp(ProductSaleRepository productSaleRepository, AccountService accountService, ProductService productService) {
        this.productSaleRepository = productSaleRepository;
        this.accountService = accountService;
        this.productService = productService;
    }

    @Override
    public String createProductSale(ProductSaleDTO productSaleDTO) throws Exception {
        Account userAccount = accountService.findByEmail(productSaleDTO.clientEmail());
        List<ProductSaleDetail> saleDetails = new ArrayList<>();
        float total = 0.0f;
        for (ProductItemDTO productItem : productSaleDTO.products()) {
            Product product = productService.findProductById(productItem.id());
            if (product.validateAmount(productItem.quantity())) {
                ProductSaleDetail productSaleDetail = ProductSaleDetail.builder()
                        .idProduct(product.getId())
                        .productName(product.getName())
                        .quantity(productItem.quantity())
                        .subtotal(productItem.quantity() * product.getUnitPrice())
                        .build();
                saleDetails.add(productSaleDetail);
                total += productSaleDetail.getSubtotal();

            } else {
                throw new Exception("Cantidad de productos no valida");
            }

        }
        ProductSale productSale = new ProductSale(total, LocalDateTime.now(), saleDetails, userAccount.getId());
        return productSaleRepository.save(productSale).getId();
    }
}
