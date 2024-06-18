package ru.skypro.homework.exception;

public class AdsNotFoundException extends RuntimeException {
    public AdsNotFoundException(String message) {
        super(message);
    }

    public AdsNotFoundException(Integer id) {
        super(String.format("Объявление c id=%s не найдено", id));
    }
}
