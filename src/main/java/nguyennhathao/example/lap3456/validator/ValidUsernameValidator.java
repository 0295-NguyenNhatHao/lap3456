package nguyennhathao.example.lap3456.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import nguyennhathao.example.lap3456.repository.IUserRepository;
import nguyennhathao.example.lap3456.validator.annotation.ValidUsername;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidUsernameValidator implements ConstraintValidator<ValidUsername, String> {
    @Autowired
    private IUserRepository userRepository;
    @Override
    public boolean isValid(String username, ConstraintValidatorContext context){
        if(userRepository==null)
            return true;
        return userRepository.findByUsername(username)==null;

    }
}
