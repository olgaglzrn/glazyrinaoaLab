package tech.reliab.course.glazyrinaoa.bank.exception;

public class TransferException extends RuntimeException {
    public TransferException(String msg) {super("Ошибка (TransferException): " + msg);}
}
