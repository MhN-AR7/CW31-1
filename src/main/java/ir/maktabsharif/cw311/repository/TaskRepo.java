package ir.maktabsharif.cw311.repository;

import ir.maktabsharif.cw311.dao.TaskDAO;

import java.util.List;
import java.util.Optional;

public interface TaskRepo {
    TaskDAO create(TaskDAO taskDAO);
    Optional<TaskDAO> findById(Long id);
    List<TaskDAO> findAll();
}
