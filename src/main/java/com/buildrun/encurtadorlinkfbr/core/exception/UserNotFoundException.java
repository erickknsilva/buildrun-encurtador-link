package com.buildrun.encurtadorlinkfbr.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class UserNotFoundException extends ToDomainException {

    @Override
    public ProblemDetail toProblemDetail() {
        var problem = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problem.setTitle("User not found Exception.");
        problem.setDetail("User not found.");
        return problem;
    }


}
