package med.voll.api.patient;

import med.voll.api.adress.Adress;

public record DataPatientRes(
    Long id,
    String name,
    String email,
    String phone,
    String cpf,
    Adress adress
) {

    public DataPatientRes(Patient patient) {
        this(
            patient.getId(),
            patient.getName(),
            patient.getEmail(),
            patient.getPhone(),
            patient.getCpf(),
            patient.getAdress()
        );
    }
    
}
