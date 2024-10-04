package co.edu.uniquindio.peluqueria.dto.productdto;

public record ProductItemDTO(
        String id,
        String productName,
        int quantity,
        float unitPrice
) {
}
