package com.ekojean.patikaclone.Services;

import com.ekojean.patikaclone.Entities.Patika;
import com.ekojean.patikaclone.Interfaces.IRepositoryDao;
import com.ekojean.patikaclone.Interfaces.IServices;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatikaServices implements IServices<Patika> {

    private final IRepositoryDao<Patika> repositoryDao;

    public PatikaServices(IRepositoryDao<Patika> repositoryDao) {
        this.repositoryDao = repositoryDao;
    }

    @Override
    public List<Patika> getList(String findText) {
        if (findText != null)
            return repositoryDao.getFindList(findText);
        else
            return repositoryDao.getList();
    }

    @Override
    public boolean add(Patika patika) {
        return repositoryDao.add(patika);
    }

    @Override
    public boolean update(Patika patika) {
        return repositoryDao.update(patika);
    }

    @Override
    public boolean delete(Patika patika) {
        return repositoryDao.delete(patika);
    }
}
