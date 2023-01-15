package es.upsa.dasi.web.controllers.beans;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.ws.rs.FormParam;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.transform.Source;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SocioBean{

    @FormParam("nombre")
    @NotNull(message = "{field.nombre} : {jakarta.validation.constraints.NotNull.message}")
    @NotBlank(message = "{field.nombre}: {jakarta.validation.constraints.NotBlank.message}")
    @Size(max = 100, min = 3,message = "${field.nombre}: {jakarta.validation.constraints.Size.message}")
    String nombre;

    @FormParam("email")
    @Size(max = 50, min = 4,message = "{field.email}: {jakarta.validation.constraints.Size.message}")
    @NotNull(message = "{field.email} : {jakarta.validation.constraints.NotNull.message}")
    @NotBlank(message = "{field.email}: {jakarta.validation.constraints.NotBlank.message}")
    String email;

    @FormParam("saldo")
    @Digits(integer = 4, fraction = 2, message = "{field.saldo}: {integer} {jakarta.validation.constraints.Digits.message}")
    String saldo;

    public double getSaldoAsDouble(){return Double.parseDouble(saldo);}
}
