package tech.reliab.course.glazyrinaoa.bank.service;

import java.math.BigDecimal;

import tech.reliab.course.glazyrinaoa.bank.entity.User;

public interface UserService {
    User create(User user);

    double calculateCreditRating(User user);
}
