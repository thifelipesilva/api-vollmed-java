package med.voll.api.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import med.voll.api.domain.appointment.DataDetailsAppointment;
import med.voll.api.domain.appointment.DataScheduleAppointment;
import med.voll.api.domain.appointment.ScheduleAppointmentService;
import med.voll.api.domain.doctor.Specialty;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class AppointmentControllerTest {
    
    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<DataScheduleAppointment> dataAppointementJSON;

    @Autowired
    private JacksonTester<DataDetailsAppointment> dataDetailsAppointmentRES;

    @MockBean
    private ScheduleAppointmentService scheduleAppointmentService;

    @Test
    @DisplayName("Should return http status code 400 when information is invalid")
    @WithMockUser
    void appointment_1() throws Exception {
        var response = mvc.perform(post("/appointment")).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    } 

    @Test
    @DisplayName("Should return http status code 200 when informations is ok")
    @WithMockUser
    void appointment_2() throws Exception {
        var date = LocalDateTime.now().plusHours(1);
        var specialty = Specialty.CARDIOLOGY;

        var dataDetailsAppointment = new DataDetailsAppointment(null, 2l, 5l, date);
        when(scheduleAppointmentService.schedule(any())).thenReturn(dataDetailsAppointment);

        var response = mvc
                .perform(
                    post("/appointment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dataAppointementJSON.write(
                            new DataScheduleAppointment(2l, 5l, date, specialty)
                        ).getJson()
                    )
                        
                )
                .andReturn()
                .getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var resJSON = dataDetailsAppointmentRES.write(new DataDetailsAppointment(null, 2l, 5l, date)).getJson();
        assertThat(response.getContentAsString()).isEqualTo(resJSON);

        

    }
}
