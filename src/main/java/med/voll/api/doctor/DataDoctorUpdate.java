package med.voll.api.doctor;

import jakarta.validation.constraints.NotNull;
import med.voll.api.adress.DataAdress;

public record DataDoctorUpdate(
    @NotNull
    Long id,
    String name,
    String phone,
    DataAdress adress
) {
    
}
