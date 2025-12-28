package com.buildrun.encurtadorlinkfbr.adapter.in.web.dto;

import com.buildrun.encurtadorlinkfbr.core.domain.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record CreateUserRequest(

        @Email(message = "Insira um email valido exemplo: example@gmail.com")
        @NotBlank(message = "Insira o email")
        @Length(min = 10, max = 53, message = "O nickname deve ter no maximo {max} e minimo {min} caracteres")
        String email,

        @NotBlank(message = "Insira o nickname")
        @Length(min = 5, max = 50, message = "O nickname deve ter no maximo {max} e minimo {min} caracteres")
        String nickname,

        @NotBlank(message = "Insira a senha")
        @Length(min = 8, max = 64, message = "O nickname deve ter no maximo {max} e minimo {min} caracteres")
        String password

) {

    public User toDomain(){
        return new User(email,nickname,password);
    }

}
