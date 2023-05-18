package med.voll.api.domain.appointment.validationAppointment;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.domain.ValidationException;
import med.voll.api.domain.appointment.DataScheduleAppointment;
import med.voll.api.domain.patient.PatientRepository;

//Não permitir o agendamento de consultas com pacientes inativos no sistema;
@Component
public class PatientActive implements ValidatorScheduleAppointemnet {
    @Autowired
    private PatientRepository repository;

    public void validation(DataScheduleAppointment data) {
        var patientIsActive = repository.findActiveByID(data.idPatient());

        if (!patientIsActive) {
            throw new ValidationException("O paciente não esta ativo no sistema");
        }
    }
}
