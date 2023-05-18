package med.voll.api.domain.appointment.validationAppointment;

import java.time.DayOfWeek;

import org.springframework.stereotype.Component;

import med.voll.api.domain.ValidationException;
import med.voll.api.domain.appointment.DataScheduleAppointment;

//O horário de funcionamento da clínica é de segunda a sábado, das 07:00 às 19:00;
@Component
public class HourAndDay implements ValidatorScheduleAppointemnet {
    
    public void validation(DataScheduleAppointment data) {
        var dataAppointement = data.data();

        var sunday = dataAppointement.getDayOfWeek().equals(DayOfWeek.SUNDAY);      
        var beforeOpen = dataAppointement.getHour() < 7;
        var afterClosed = dataAppointement.getHour() > 18;

        if (sunday || beforeOpen || afterClosed) {
            throw new ValidationException("O dia da consulta deve ser diferente de domingo e das 7 as 18 horas");
        }
    }
}
