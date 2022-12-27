package es.upsa.dasi.model;

import lombok.*;

@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Producto {
    String codigo;
    String nombre;
    String descripcion;
    int stock;
    double precio;
}
