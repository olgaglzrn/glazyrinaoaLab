package tech.reliab.course.glazyrinaoa.bank.exception;

public class ExportException extends RuntimeException {
    public ExportException(String msg) {super("Ошибка (ExportException): " + msg);}
}
