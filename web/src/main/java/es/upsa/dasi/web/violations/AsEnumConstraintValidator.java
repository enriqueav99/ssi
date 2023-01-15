package es.upsa.dasi.web.violations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.HashSet;
import java.util.Set;

public class AsEnumConstraintValidator implements ConstraintValidator<AsEnum, String> {
    Set<String> values;
    @Override
    public void initialize(AsEnum annotation) {
        values = new HashSet<>();

        Enum[] enumConstants = annotation.enumClass().getEnumConstants();
        for (Enum enumConstant : enumConstants) {
            values.add(enumConstant.name());
        }
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) return true;
        return values.contains(value);
    }
}
