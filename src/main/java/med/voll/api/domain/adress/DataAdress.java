package med.voll.api.domain.adress;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DataAdress(

    @NotBlank
    String street_address,

    @NotBlank 
    String neighborhood, 
    
    @NotBlank
    @Pattern(regexp = "\\d{8}")
    String zip_code,
    
    @NotBlank
    String city, 
    
    @NotBlank
    String state, 

    String address_line2, 
    String number
) {
    
}
