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
import java.lang.annotation.Annotation;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDto implements Validator {
    private int id;
    private String code;
    private String name;
    private String dateOfBirth;
    private String experience;
    private String avatar;
    private Position position;
    private Team team;



    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }
    private final String CODE = "^CT-\\d{1,2}$";
    @Override
    public void validate(Object target, Errors errors) {
        PlayerDto playerDto = (PlayerDto) target;
        if (!playerDto.code.matches(CODE)){
            errors.rejectValue("code",null,"Code Regex: CT-XX (X: 0-9)");
        }
    }
}
