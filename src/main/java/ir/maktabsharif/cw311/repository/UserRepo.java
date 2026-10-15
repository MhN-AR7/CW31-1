package ir.maktabsharif.cw311.repository;

import ir.maktabsharif.cw311.dao.UserDAO;

import java.util.List;
import java.util.Optional;

public interface UserRepo {
    UserDAO create(UserDAO userDAO);
    Optional<UserDAO> findById(Long id);
    List<UserDAO> findAll();
}
