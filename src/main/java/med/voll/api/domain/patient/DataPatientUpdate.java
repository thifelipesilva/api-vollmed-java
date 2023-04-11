package med.voll.api.domain.patient;

import lombok.NonNull;
import med.voll.api.domain.adress.DataAdress;

public record DataPatientUpdate(
    @NonNull
    Long id,
    String name,
    String phone,
    DataAdress adress
) {
    
}
