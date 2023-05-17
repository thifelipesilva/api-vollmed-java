package med.voll.api.domain.appointment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import med.voll.api.domain.ValidationException;
import med.voll.api.domain.doctor.Doctor;
import med.voll.api.domain.doctor.DoctorRepository;
import med.voll.api.domain.patient.PatientRepository;

@Service
public class ScheduleAppointment {
    @Autowired
    private AppointmentRepository repository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    public void schedule(DataScheduleAppointment data) {
        if (!patientRepository.existsById(data.idPatient())) {
            throw new ValidationException("Id don't exist in db");
        }

        if (data.idDoctor() !=null && !doctorRepository.existsById(data.idDoctor())) {
            throw new ValidationException("Id don't exist in db");
        }
        
        var patient = patientRepository.getReferenceById(data.idPatient());
        var doctor = doctorRepository.getReferenceById(data.idDoctor());
        var appointment = new Appointment(null, doctor, patient, data.data());
        repository.save(appointment);
    }

    public Doctor chosseDoctor(DataScheduleAppointment data) {
        if (data.idDoctor() != null) {
            return doctorRepository.getReferenceById(data.idDoctor());
        }

        if (data.specialty() == null) {
            throw new ValidationException("Specialty is required when doctor doesn't exist");
        }

        return doctorRepository.chosseRandomDoctor(data.specialty(), data.data());
    }
}
