package dev.iamwallace.user.infrastructure.exceptions;

public class ConflictExcepetion extends RuntimeException {
  public ConflictExcepetion(String message) {
    super(message);
  }

  public ConflictExcepetion(String message, Throwable throwable) {
    super(message);
  }
}
