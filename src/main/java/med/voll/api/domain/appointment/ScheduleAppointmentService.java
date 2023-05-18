package med.voll.api.domain.appointment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import med.voll.api.domain.ValidationException;
import med.voll.api.domain.appointment.validationAppointment.ValidatorScheduleAppointemnet;
import med.voll.api.domain.doctor.Doctor;
import med.voll.api.domain.doctor.DoctorRepository;
import med.voll.api.domain.patient.PatientRepository;

@Service
public class ScheduleAppointmentService {
    @Autowired
    private AppointmentRepository repository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private List<ValidatorScheduleAppointemnet> validators;

    public void cancel(DataCancelAppointment data) {
        if (repository.existsById(data.idAppointment())) {
            throw new ValidationException("Appointement no exists");
        }

        var appointment = repository.getReferenceById(data.idAppointment());
        appointment.cancelReason(data.reason());
    }

    public DataDetailsAppointment schedule(DataScheduleAppointment data) {
        if (!patientRepository.existsById(data.idPatient())) {
            throw new ValidationException("Id don't exist in db");
        }

        if (data.idDoctor() !=null && !doctorRepository.existsById(data.idDoctor())) {
            throw new ValidationException("Id don't exist in db");
        }

        validators.forEach(v -> v.validation(data));
        
        var patient = patientRepository.getReferenceById(data.idPatient());
        var doctor = doctorRepository.getReferenceById(data.idDoctor());

        if (doctor == null) {
            throw new ValidationException("doens't exists doctor in this date");
        }

        var appointment = new Appointment(null, doctor, patient, data.data(), null);
        repository.save(appointment);

        return new DataDetailsAppointment(appointment);
    }

    //A escolha do médico é opcional, sendo que nesse caso o sistema deve escolher aleatoriamente algum médico disponível na data/hora preenchida.
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
