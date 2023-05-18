package med.voll.api.domain.appointment.validationAppointment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.domain.ValidationException;
import med.voll.api.domain.appointment.DataScheduleAppointment;
import med.voll.api.domain.doctor.DoctorRepository;

//Não permitir o agendamento de consultas com médicos inativos no sistema;
@Component
public class DoctorActive implements ValidatorScheduleAppointemnet {
    @Autowired
    private DoctorRepository repository;
    
    public void validation(DataScheduleAppointment data) {

        //escolha do medico opcional
        if (data.idDoctor() == null) {
            return;
        }

        var doctorIsActive = repository.findActiveByID(data.idDoctor());

        if (!doctorIsActive) {
            throw new ValidationException("O medico não esta ativo no sistema");
        }
    }
}
