package com.buildrun.encurtadorlinkfbr.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class LinkAlreadyExistException extends ToDomainException {

    @Override
    public ProblemDetail toProblemDetail() {
        var problem = ProblemDetail.forStatus(HttpStatus.CONFLICT);
        problem.setTitle("Link already exists.");
        problem.setDetail("There is a link slug with this slug. Try another slug.");
        return problem;
    }

}
