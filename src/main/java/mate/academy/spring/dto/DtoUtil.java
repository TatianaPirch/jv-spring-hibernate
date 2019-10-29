package mate.academy.spring.dto;

import mate.academy.spring.entity.User;
import org.springframework.stereotype.Component;

@Component
public class DtoUtil {
    public User convertDto(UserDto userDto) {
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        return user;
    }
}
