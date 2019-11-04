package mate.academy.spring.lib;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import mate.academy.spring.dto.UserDto;
import mate.academy.spring.lib.annotations.PasswordMatches;

public class PasswordMatchesValidator
        implements ConstraintValidator<PasswordMatches, UserDto> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
    }

    @Override
    public boolean isValid(UserDto userDto, ConstraintValidatorContext context) {
        return userDto.getPassword().equals(userDto.getRepeatPassword());
    }
}
