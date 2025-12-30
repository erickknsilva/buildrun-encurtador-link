package com.buildrun.encurtadorlinkfbr.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class LoginException extends ToDomainException {

    @Override
    public ProblemDetail toProblemDetail() {
        var problem = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problem.setTitle("Login Exception.");
        problem.setDetail("Invalid email or password.");
        return problem;
    }


}
