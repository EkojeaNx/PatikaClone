package com.ekojean.patikaclone.Interfaces;

import java.util.List;

public interface IController<T extends IEntity> {
    public List<T> getList(String findText);
    public boolean add(T entity);
    public boolean update(T entity);
    public boolean delete(T entity);
}
