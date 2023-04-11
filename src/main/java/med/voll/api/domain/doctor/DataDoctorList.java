package med.voll.api.domain.doctor;

public record DataDoctorList(
    Long id,
    String name,
    String email,
    String crm,
    Specialty specialty
) {

    public DataDoctorList(Doctor doctor) {
        this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getSpecialty());
    }
    
}
