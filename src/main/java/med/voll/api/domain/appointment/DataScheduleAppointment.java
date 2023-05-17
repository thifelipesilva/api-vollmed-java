package med.voll.api.domain.appointment;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.doctor.Specialty;

public record DataScheduleAppointment(
    Long idDoctor,
    
    @NotNull
    Long idPatient,

    @NotNull
    @Future
    LocalDateTime data,
    
    Specialty specialty
) {
}
