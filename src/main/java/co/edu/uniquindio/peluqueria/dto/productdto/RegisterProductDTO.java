package co.edu.uniquindio.peluqueria.dto.productdto;

public record RegisterProductDTO(
        String productName,
        int quantity,
        float unitPrice
) {
}
