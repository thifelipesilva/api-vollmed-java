package med.voll.api.domain.appointment.validationAppointment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.domain.ValidationException;
import med.voll.api.domain.appointment.AppointmentRepository;
import med.voll.api.domain.appointment.DataScheduleAppointment;

//Não permitir o agendamento de mais de uma consulta no mesmo dia para um mesmo paciente;
@Component
public class PatientWithoutAnotherAppointmentOnTheDay implements ValidatorScheduleAppointemnet {
    
    @Autowired
    private AppointmentRepository repository;

    public void validation(DataScheduleAppointment data) {
        var firstAppointment = data.data().withHour(7);
        var lastAppointment = data.data().withHour(18);
        var patientHasOtherAppointmentInThisDay = repository.existsByPatientIdAndDataBetween(data.idPatient(), firstAppointment, lastAppointment);
    
        if (patientHasOtherAppointmentInThisDay) {
            throw new ValidationException("Paciente ja possui um consulta agendada");
        }
    }

}
