package com.math012.crudpostgressql.infra.exceptions;

public class RequestException extends RuntimeException {
  public RequestException(String message) {
    super(message);
  }
}
