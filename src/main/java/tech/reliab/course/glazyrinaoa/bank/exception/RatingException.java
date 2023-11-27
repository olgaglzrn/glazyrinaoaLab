package tech.reliab.course.glazyrinaoa.bank.exception;

public class RatingException extends RuntimeException  {
    public RatingException(String msg) {
        super("Ошибка (RatingException): " + msg);
    }

}