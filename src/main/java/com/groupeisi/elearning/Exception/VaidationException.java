package com.groupeisi.elearning.Exception;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.core.MethodParameter;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;


@Getter
@Setter
public class VaidationException extends MethodArgumentNotValidException {
    private String message;

    public VaidationException(MethodParameter parameter, BindingResult bindingResult) {
        super(parameter, bindingResult);
    }
}