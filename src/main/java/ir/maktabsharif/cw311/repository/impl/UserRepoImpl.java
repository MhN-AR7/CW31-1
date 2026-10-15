package ir.maktabsharif.cw311.repository.impl;

import ir.maktabsharif.cw311.dao.UserDAO;
import ir.maktabsharif.cw311.repository.UserRepo;
import ir.maktabsharif.cw311.util.HibernateUtil;

import java.util.List;
import java.util.Optional;

public class UserRepoImpl implements UserRepo {
    @Override
    public UserDAO create(UserDAO userDAO) {
        return HibernateUtil.inTxReturn(
                em -> {
                    em.persist(userDAO);
                    return userDAO;
                }
        );
    }

    @Override
    public Optional<UserDAO> findById(Long id) {
        return Optional.ofNullable(
                HibernateUtil.read(
                        em ->
                                em.find(UserDAO.class, id)
                )
        );
    }

    @Override
    public List<UserDAO> findAll() {
        return HibernateUtil.read(
                em ->
                        em.createQuery(
                                "FROM UserDAO",
                                UserDAO.class
                        )
                                .getResultList()
        );
    }

    @Override
    public Optional<UserDAO> findByUsername(String username) {
        return Optional.ofNullable(
                HibernateUtil.read(
                        em ->
                                em.createQuery(
                                                "SELECT u FROM UserDAO u WHERE u.username = :username",
                                                UserDAO.class
                                        )
                                        .setParameter("username", username)
                                        .getSingleResult()
                )
        );
    }
}
