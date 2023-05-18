package med.voll.api.domain.appointment.validationAppointment;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import java.time.Duration;

import med.voll.api.domain.ValidationException;
import med.voll.api.domain.appointment.DataScheduleAppointment;

//As consultas devem ser agendadas com antecedência mínima de 30 minutos;
@Component
public class HoursAdviceAppointment implements ValidatorScheduleAppointemnet{

    public void validation(DataScheduleAppointment data) {

        var dateAppointement = data.data();

        var now = LocalDateTime.now();
        var minutesDifferences = Duration.between(now, dateAppointement).toMinutes();

        if(minutesDifferences < 30) {
            throw new ValidationException("Consulta deve ser marcada com 30 min de antecedencia.");
        }
    }
}