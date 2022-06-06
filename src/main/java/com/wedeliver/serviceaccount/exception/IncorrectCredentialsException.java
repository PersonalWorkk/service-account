package com.wedeliver.serviceaccount.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Incorrect credentials")
public class IncorrectCredentialsException extends RuntimeException {
}
