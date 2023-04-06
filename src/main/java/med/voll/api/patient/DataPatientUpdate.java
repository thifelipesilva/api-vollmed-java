package med.voll.api.patient;

import lombok.NonNull;
import med.voll.api.adress.DataAdress;

public record DataPatientUpdate(
    @NonNull
    Long id,
    String name,
    String phone,
    DataAdress adress
) {
    
}
