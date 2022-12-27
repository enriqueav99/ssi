package es.upsa.dasi.dtos;

import lombok.*;

@Builder(toBuilder = true)
@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
public class UnidentifiedSocio {
    String nombre;
    String email;
}
