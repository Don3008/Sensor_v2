package siemens.sensor.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;


import javax.validation.ConstraintViolation;
import java.util.Set;
import javax.validation.Validator;

@Service
public class MessageValidator implements org.springframework.validation.Validator {
    @Autowired
    private Validator validator;

    @Override
    public boolean supports(Class<?> aClass) {
        return Message.class.equals(aClass);
    }



    @Override
    public void validate(Object obj, Errors errors) {
        Set<ConstraintViolation<Object>> validates = validator.validate(obj);

        for (ConstraintViolation<Object> constraintViolation : validates) {
            String propertyPath = constraintViolation.getPropertyPath().toString();
            String message = constraintViolation.getMessage();
            errors.rejectValue(propertyPath, "", message);
        }

        Message m = (Message) obj;
        if (m.getLatitude() < -90.0 || m.getLatitude() > 90.0) {
            errors.rejectValue("latitude", "", "Широта должна быть в пределе от -90.0˚ до 90.0˚");
        }
        if (m.getLongitude() < -180.0 || m.getLongitude() > 180.0) {
            errors.rejectValue("longitude", "", "Долгота должна быть в пределе от -180.0˚ до 180.0˚");
        }
        if (m.getTemperature() < -80.0 || m.getTemperature() > 80.0)  {
            errors.rejectValue("temperature", "", "Температура должна быть в пределе от -80˚C до +80˚C");
        }
    }
}
