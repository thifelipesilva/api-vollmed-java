package med.voll.api.adress;
    
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Adress {
    
    private String street_address;
    private String neighborhood;
    private String zip_code;
    private String city;
    private String state;

   
    private String address_line2;
    
    private String number;

    public Adress(DataAdress adress) {
        this.street_address = adress.street_address();
        this.neighborhood = adress.neighborhood();
        this.zip_code = adress.zip_code();
        this.city = adress.city();
        this.state = adress.state();
        this.address_line2 = adress.address_line2();
        this.number = adress.number();
    }

    public void updateDataAdress(DataAdress data) {

        if (data.street_address() != null) {
            this.street_address = data.street_address();
        }
        if (data.neighborhood() != null) {
            this.neighborhood = data.neighborhood();
        }
        if (data.zip_code() != null) {
            this.zip_code = data.zip_code();
        }
        if (data.city() != null) {
            this.city = data.city();
        }
        if (data.state() != null) {
            this.state = data.state();
        }
        if (data.address_line2() != null) {
            this.address_line2 = data.address_line2();
        }
        if (data.number() != null) {
            this.number = data.number();
        }
    }
    

}
