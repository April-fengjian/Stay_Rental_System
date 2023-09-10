package com.laioffer.staybooking.exception;

//RuntimeException是运行是才能发现的异常，ex. IndexOutofBound
public class StayNotExistException extends RuntimeException {
    public StayNotExistException(String message) {
        super(message);
    }
}