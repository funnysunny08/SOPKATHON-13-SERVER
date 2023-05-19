package sopt.sopkathon13.Server.exception.model;

import sopt.sopkathon13.Server.exception.Error;

public class NotFoundException extends SoptException {
    public NotFoundException(Error error, String message) {
        super(error, message);
    }
}
