package med.voll.api.domain.doctor;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.adress.DataAdress;

public record DataDoctorUpdate(
    @NotNull
    Long id,
    String name,
    String phone,
    DataAdress adress
) {
    
}
