package med.voll.api.domain.appointment;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    Boolean existsByDoctorIdAndData(Long idDoctor, LocalDateTime data);

    Boolean existsByPatientIdAndDataBetween(Long idPatient, LocalDateTime firstAppointment, LocalDateTime lastAppointment);
    
}
