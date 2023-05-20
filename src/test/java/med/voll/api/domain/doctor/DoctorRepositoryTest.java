package med.voll.api.domain.doctor;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import jakarta.persistence.EntityManager;
import med.voll.api.domain.adress.DataAdress;
import med.voll.api.domain.appointment.Appointment;
import med.voll.api.domain.patient.DataPatientCreate;
import med.voll.api.domain.patient.Patient;

import static org.assertj.core.api.Assertions.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;



@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class DoctorRepositoryTest {

    @Autowired
    private DoctorRepository doctorRepository;  
    
    @Autowired
    private EntityManager em;

    @Test
    @DisplayName("Should return null when a unique doctor registred is busy in the date")
    void chosseRandomDoctor_1() {

        var nextMondayAt10AM = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);

        var doctor = registerDoctor("Doctor", "doctor@vollmed.com", Specialty.CARDIOLOGY);
        var patient = registerPatient("Patient", "patient@vollmed.com", "98765431219");
        registerAppointment(doctor, patient, nextMondayAt10AM);
        var doctorBusy = doctorRepository.chosseRandomDoctor(Specialty.CARDIOLOGY, nextMondayAt10AM);
        assertThat(doctorBusy).isNull();

    }

    @Test
    @DisplayName("should return a doctor available on the date")
    void chosseRandomDoctor_2() {

        var nextMondayAt10AM = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);

        var doctor = registerDoctor("Doctor", "doctor@vollmed.com", Specialty.CARDIOLOGY);
        var doctorAvailable = doctorRepository.chosseRandomDoctor(Specialty.CARDIOLOGY, nextMondayAt10AM);
        assertThat(doctorAvailable).isEqualTo(doctor);
    }




    private void registerAppointment(Doctor doctor, Patient patient, LocalDateTime date) {
        em.persist(new Appointment(null, doctor, patient, date, null));
    }

    private Doctor registerDoctor(String name, String email, Specialty specialty) {
        var doctor = new Doctor(dataDoctor(name, email, specialty));
        em.persist(doctor);
        return doctor;
    }

    private DataDoctorCreate dataDoctor(String name, String email, Specialty specialty) {
        return new DataDoctorCreate(name, email, "61999999999", "123444", specialty, dataAdress());
    }

    

    private Patient registerPatient(String name, String email, String cpf) {
        var patient = new Patient(dataPatient(name, email, cpf));
        em.persist(patient);
        return patient;
    }

    private DataPatientCreate dataPatient(String name, String email, String cpf) {
        return new DataPatientCreate(
            name,
            email,
            cpf,
            "61999999999",
            dataAdress()
        );
    }


    private DataAdress dataAdress() {
        return new DataAdress(
            "rua xpto",
            "bairro",
            "00000000",
            "Brasilia",
            "DF",
            null,
            null
        );
    }
}


