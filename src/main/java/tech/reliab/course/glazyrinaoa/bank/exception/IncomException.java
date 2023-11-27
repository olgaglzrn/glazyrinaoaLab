package tech.reliab.course.glazyrinaoa.bank.exception;

public class IncomException extends RuntimeException  {
    public IncomException(String msg) {
        super("Ошибка (IncomException): " + msg);
    }
}
