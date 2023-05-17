package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import med.voll.api.domain.appointment.DataCancelAppointment;
import med.voll.api.domain.appointment.DataDetailsAppointment;
import med.voll.api.domain.appointment.DataScheduleAppointment;
import med.voll.api.domain.appointment.ScheduleAppointment;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {
    @Autowired
    private ScheduleAppointment scheduleAppointment;

    @PostMapping
    @Transactional
    public ResponseEntity schedule(@RequestBody @Valid DataScheduleAppointment data) {
        scheduleAppointment.schedule(data);
        return ResponseEntity.ok(new DataDetailsAppointment(null, null, null, null));
    }


@DeleteMapping
@Transactional
public ResponseEntity cancel(@RequestBody @Valid DataCancelAppointment data) {
    scheduleAppointment.cancel(data);
    return ResponseEntity.noContent().build();
}
}
