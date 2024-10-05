package co.edu.uniquindio.peluqueria.dtos.productdto;

public record ProductItemDTO(
        String id,
        String productName,
        int quantity,
        float unitPrice
) {
}
