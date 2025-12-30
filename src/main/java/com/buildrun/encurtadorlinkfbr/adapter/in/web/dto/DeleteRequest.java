package com.buildrun.encurtadorlinkfbr.adapter.in.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DeleteRequest(
        @NotBlank(message = "Insira o email.")
        @Email(message = "Deve ser um e-mail valido exemplo@gmail.com")
        String email
) {
}
