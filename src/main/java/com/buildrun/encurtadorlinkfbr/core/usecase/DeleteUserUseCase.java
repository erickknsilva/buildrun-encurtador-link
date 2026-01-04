package com.buildrun.encurtadorlinkfbr.core.usecase;

import com.buildrun.encurtadorlinkfbr.core.exception.UserNotFoundException;
import com.buildrun.encurtadorlinkfbr.core.port.in.DeleteUserPortIn;
import com.buildrun.encurtadorlinkfbr.core.port.out.UserRepositoryPortOut;
import org.springframework.stereotype.Component;

@Component
public class DeleteUserUseCase implements DeleteUserPortIn {

    private final UserRepositoryPortOut userRepositoryPortOut;

    public DeleteUserUseCase(UserRepositoryPortOut userRepositoryPortOut) {
        this.userRepositoryPortOut = userRepositoryPortOut;
    }

    @Override
    public void execute(String email) {

        userRepositoryPortOut.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);

        userRepositoryPortOut.deleteById(email);
    }


}
