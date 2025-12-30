package com.buildrun.encurtadorlinkfbr.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class DomainException extends RuntimeException {

    public ProblemDetail toProblemDetail() {
        var problem = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        problem.setTitle("Encurator-Link internal server error.");
        return problem;
    }

}
