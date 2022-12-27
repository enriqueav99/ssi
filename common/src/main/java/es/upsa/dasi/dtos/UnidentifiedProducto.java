package es.upsa.dasi.dtos;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UnidentifiedProducto {
    String nombre;
    String descripcion;
    int stock;
    double precio;
}
