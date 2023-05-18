package med.voll.api.domain.appointment.validationAppointment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.domain.ValidationException;
import med.voll.api.domain.appointment.AppointmentRepository;
import med.voll.api.domain.appointment.DataScheduleAppointment;

//Não permitir o agendamento de uma consulta com um médico que já possui outra consulta agendada na mesma data/hora;
@Component
public class DoctorBusyAtThisTime implements ValidatorScheduleAppointemnet {
    @Autowired
    private AppointmentRepository repository;

    public void validation(DataScheduleAppointment data) {
        var doctorBusyTime = repository.existsByDoctorIdAndData(data.idDoctor(), data.data());

        if (doctorBusyTime) {
            throw new ValidationException("Medico possui consulta nessa data.");
        }
    }
}
