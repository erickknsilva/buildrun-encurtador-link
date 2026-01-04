package com.buildrun.encurtadorlinkfbr.core.usecase;

import com.buildrun.encurtadorlinkfbr.adapter.in.web.dto.LoginRequest;
import com.buildrun.encurtadorlinkfbr.adapter.in.web.dto.LoginResponse;
import com.buildrun.encurtadorlinkfbr.core.exception.LoginException;
import com.buildrun.encurtadorlinkfbr.core.port.in.AuthenticatePortIn;
import com.buildrun.encurtadorlinkfbr.core.port.out.UserRepositoryPortOut;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class AuthenticationUseCase implements AuthenticatePortIn {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtEncoder jwtEncoder;
    private final UserRepositoryPortOut userRepositoryPortOut;

    public AuthenticationUseCase(BCryptPasswordEncoder bCryptPasswordEncoder, JwtEncoder jwtEncoder, UserRepositoryPortOut repositoryPortOut) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jwtEncoder = jwtEncoder;
        this.userRepositoryPortOut = repositoryPortOut;
    }

    @Override
    public LoginResponse execute(LoginRequest loginRequest) {

        //findByUsername - enontrar o email
        var user = userRepositoryPortOut.findByEmail(loginRequest.email()).orElseThrow(()->  new LoginException());

        /* 1 - comparar senha - comparar a senha
         * 2 - senha nao valida, retornar erro de credenciais invalida
         */
        var matchPassword= bCryptPasswordEncoder.matches(loginRequest.password(), user.getPassword());

        if(!matchPassword) {
            throw new LoginException();
        }

        var expiresIn = 300L;
        //gerar o token jwt
       var claimsJwt = JwtClaimsSet.builder()
               .subject(user.getUserId().toString())
               .issuer("buildrun-encurtador-link")
               .claim("email", user.getEmail())
               .expiresAt(Instant.now().plusSeconds(expiresIn))
                .build();

       var parametersJwt = JwtEncoderParameters.from(claimsJwt);

       var tokenJwt= jwtEncoder.encode(parametersJwt);

        return new LoginResponse(tokenJwt.getTokenValue(),expiresIn);
    }

}
