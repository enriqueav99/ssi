package es.upsa.dasi.model;


import lombok.*;


@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Socio {
    String codigo;
    String nombre;
    String email;
    double saldo;
}
