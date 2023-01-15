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

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductoBean {

    @FormParam("nombre")
    @NotNull(message = "{field.nombre} : {jakarta.validation.constraints.NotNull.message}")
    @NotBlank(message = "{field.nombre}: {jakarta.validation.constraints.NotBlank.message}")
    @Size(max = 100, min = 3,message = "${field.nombre}: {jakarta.validation.constraints.Size.message}")
    String nombre;

    @FormParam("descripcion")
    @NotNull(message = "{field.descripcion} : {jakarta.validation.constraints.NotNull.message}")
    @NotBlank(message = "{field.descripcion}: {jakarta.validation.constraints.NotBlank.message}")
    @Size(max = 100, min = 3,message = "${field.descripcion}: {jakarta.validation.constraints.Size.message}")
    String descripcion;

    @FormParam("precio")
    @NotNull(message = "{field.precio} : {jakarta.validation.constraints.NotNull.message}")
    @Digits(integer = 3, fraction = 1, message = "{field.precio}: {integer} {jakarta.validation.constraints.Digits.message}")
    String precio;

    @FormParam("stock")
    @Digits(integer = 3, fraction = 0, message = "{field.stock}: {integer} {jakarta.validation.constraints.Digits.message}")
    String stock;

    public double getPrecioAsDouble(){return Double.parseDouble(precio);}
    public int getStockAsInt(){return Integer.parseInt(stock);}
}
