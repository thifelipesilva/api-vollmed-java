package med.voll.api.domain.appointment;

import java.time.LocalDateTime;

public record DataDetailsAppointment(
    Long id,
    Long idPatient,
    Long idDoctor,
    LocalDateTime data
) {
}
