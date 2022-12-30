package es.upsa.dasi.resources.providers.beans;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(setterPrefix = "with")
public class ErrorMessage
{
    private String message;
}
