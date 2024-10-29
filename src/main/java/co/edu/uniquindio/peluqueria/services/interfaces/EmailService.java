package co.edu.uniquindio.peluqueria.services.interfaces;

import co.edu.uniquindio.peluqueria.dtos.accountdto.EmailDTO;

public interface EmailService {

    public void sendEmail(EmailDTO emailDTO) throws Exception;

}
