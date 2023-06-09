package med.voll.api.domain.doctor;

import med.voll.api.domain.adress.Adress;

public record DataDoctorRes(
    Long id, 
    String name, 
    String email, 
    String crm, 
    String phone, 
    Specialty specialty, 
    Adress adress
) {

    public DataDoctorRes(Doctor doctor) {
        this(
            doctor.getId(), 
            doctor.getPhone(), 
            doctor.getName(), 
            doctor.getEmail(), 
            doctor.getCrm(), 
            doctor.getSpecialty(),
            doctor.getAdress()
        );
    }
    
}
