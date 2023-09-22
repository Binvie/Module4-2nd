package com.example.playermanagement.dto;

import com.example.playermanagement.model.Position;
import com.example.playermanagement.model.Team;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.lang.annotation.Annotation;
import java.time.LocalDate;
import java.time.Period;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDto implements Validator {
    private int id;

    private String code;

    @NotNull
    @Size(min = 5, max = 100)
    private String name;

    private String dateOfBirth;

    @NotNull
    private String experience;

    @NotNull
    private String avatar;

    private Position position;

    private Team team;


    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    private final String CODE = "^CT-\\d{1,2}$";
    private final String NAME = "^([A-Z]\\w+)(\\s[A-Z]\\w+)+$";

    public boolean isValidDate() {
        LocalDate now = LocalDate.now();
        LocalDate localDate18 = now.minusYears(18);
        String date = getDateOfBirth();
        LocalDate birth = LocalDate.parse(date);
        Period period = Period.between(birth, now);
        return period.getYears() <= 60 && period.getYears() > 18 && localDate18.isAfter(birth);
    }

    @Override
    public void validate(Object target, Errors errors) {
        PlayerDto playerDto = (PlayerDto) target;
        if (!playerDto.code.matches(CODE)) {
            errors.rejectValue("code", null, "Code Regex: CT-XX (X: 0-9)");
        }
        if (!playerDto.name.matches(NAME)) {
            errors.rejectValue("name", null, "Uppercase first letter");
        }
        if (!playerDto.isValidDate()){
            errors.rejectValue("dateOfBirth", null,"Must be greater than 18 and less than 60");
        }
    }
}
