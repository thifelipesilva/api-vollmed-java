package med.voll.api.domain.doctor;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.adress.Adress;

@Table(name = "doctors")
@Entity(name = "Doctor")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Doctor {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name; 
    private String email;
    private String phone;
    private String crm;

    @Enumerated(EnumType.STRING)
    private  Specialty specialty;

    @Embedded
    private  Adress adress; 

    private Boolean active;
    
    public Doctor(DataDoctorCreate data) {
        this.name = data.name();
        this.email = data.email();
        this.phone = data.phone();
        this.crm = data.crm();
        this.specialty = data.specialty();
        this.adress = new Adress(data.adress());
        this.active = true;
    }

    public void updateData(@Valid DataDoctorUpdate data) {
        if (data.name() != null) {
            this.name = data.name();
        }

        if (data.phone() != null) {
            this.phone = data.phone();
        }

        if (data.adress() != null) {
            this.adress.updateDataAdress(data.adress());
        }
    }

    public void delete() {
        this.active = false;
    }

    
}
