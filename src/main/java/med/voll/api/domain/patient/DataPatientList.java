package med.voll.api.domain.patient;

public record DataPatientList(
    Long id,
    String name,
    String cpf
) {
    public DataPatientList(Patient patient) {
        this(patient.getId(), patient.getName(), patient.getCpf());
    }
}
