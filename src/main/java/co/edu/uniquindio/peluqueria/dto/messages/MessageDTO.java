package co.edu.uniquindio.peluqueria.dto.messages;

public record MessageDTO<T>(
        boolean error,
        T reply
) {
}
