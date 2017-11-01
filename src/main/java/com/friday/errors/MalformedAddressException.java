package com.friday.errors;

public class MalformedAddressException extends RuntimeException{

  public MalformedAddressException(String msg){
    super(msg);
  }
}
