package co.edu.uniquindio.peluqueria.dtos.productdto;

public record RegisterProductDTO(
        String productName,
        int quantity,
        float unitPrice
) {
}
