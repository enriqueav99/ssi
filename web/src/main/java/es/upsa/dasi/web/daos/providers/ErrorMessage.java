package es.upsa.dasi.web.daos.providers;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(setterPrefix = "with")
public class ErrorMessage
{
    private String message;
}
