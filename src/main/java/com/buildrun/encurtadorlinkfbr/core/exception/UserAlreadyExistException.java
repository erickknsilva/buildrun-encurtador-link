package com.buildrun.encurtadorlinkfbr.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class UserAlreadyExistException extends DomainException{

    @Override
    public ProblemDetail toProblemDetail() {
        var problem = ProblemDetail.forStatus(HttpStatus.CONFLICT);
        problem.setTitle("User already exists.");
        problem.setDetail("There is already a user with this email. Try another email.");
        return problem;
    }

}
