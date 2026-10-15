package ir.maktabsharif.cw311.repository.impl;

import ir.maktabsharif.cw311.dao.UserDAO;
import ir.maktabsharif.cw311.dto.ExistUsernameDTO;
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

    @Override
    public boolean isUserExist(String username) {
        return HibernateUtil.read(
                em ->
                        em.createQuery(
                                        "SELECT NEW ir.maktabsharif.cw311.dto.ExistUsernameDTO" +
                                                "(case when count(u) <>0 then true else false end)" +
                                                " FROM UserDAO u WHERE u.username = :username",
                                        ExistUsernameDTO.class
                                )
                                .setParameter("username", username)
                                .getSingleResult()
        ).isExist();
    }
}
