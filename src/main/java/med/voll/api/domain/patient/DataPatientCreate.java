package med.voll.api.domain.patient;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.adress.DataAdress;

public record DataPatientCreate(

    @NotBlank
    String name,

    @NotBlank
    @Email
    String email,

    @NotBlank
    String phone,

    @NotBlank
    @Pattern(regexp = "\\d{11}")
    String cpf,

    @NotNull
    @Valid
    DataAdress adress
) {
}
