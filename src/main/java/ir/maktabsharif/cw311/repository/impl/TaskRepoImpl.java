package ir.maktabsharif.cw311.repository.impl;

import ir.maktabsharif.cw311.dao.TaskDAO;
import ir.maktabsharif.cw311.repository.TaskRepo;
import ir.maktabsharif.cw311.util.HibernateUtil;

import java.util.List;
import java.util.Optional;

public class TaskRepoImpl implements TaskRepo {
    @Override
    public TaskDAO create(TaskDAO taskDAO) {
        return HibernateUtil.inTxReturn(
                em -> {
                    em.persist(taskDAO);
                    return taskDAO;
                }
        );
    }

    @Override
    public Optional<TaskDAO> findById(Long id) {
        return Optional.ofNullable(
                HibernateUtil.read(
                        em ->
                                em.find(TaskDAO.class, id)
                )
        );
    }

    @Override
    public List<TaskDAO> findAll() {
        return HibernateUtil.read(
                em ->
                        em.createQuery(
                                "FROM TaskDAO",
                                TaskDAO.class
                        )
                                .getResultList()
        );
    }
}
