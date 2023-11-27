package tech.reliab.course.glazyrinaoa.bank.exception;
public class NotFoundException extends RuntimeException  {
    public NotFoundException(String msg) {
        super("Ошибка (NotFoundException): " + msg);
    }
}
