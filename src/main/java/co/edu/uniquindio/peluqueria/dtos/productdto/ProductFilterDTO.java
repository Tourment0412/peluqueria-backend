package co.edu.uniquindio.peluqueria.dtos.productdto;

public record ProductFilterDTO(
        String productName,
        float priceLower,
        float priceUpper
) {
}
