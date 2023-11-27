package tech.reliab.course.glazyrinaoa.bank.exception;

public class CreditException extends RuntimeException  {
    public CreditException(String msg) {
        super("Ошибка (CreditException): " + msg);
    }
}
