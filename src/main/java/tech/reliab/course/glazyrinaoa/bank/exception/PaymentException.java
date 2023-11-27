package tech.reliab.course.glazyrinaoa.bank.exception;

public class PaymentException extends RuntimeException  {
    public PaymentException(String msg) {
        super("Ошибка (PaymentException): " + msg);
    }
}
