package nguyennhathao.example.lap3456.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import nguyennhathao.example.lap3456.repository.User;
import nguyennhathao.example.lap3456.validator.annotation.ValidUserId;

public class ValidUserIdValidator implements ConstraintValidator<ValidUserId ,User> {
    @Override
    public boolean isValid(User user, ConstraintValidatorContext context){
        if(user==null)
            return true;
        return user.getId() != null;
    }
}
