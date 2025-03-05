package tn.esprit.interfaces;

import java.util.List;

public interface Iservice<T>  {
    public void add(T t);
    public List<T> getAll();

    void delete(int id);

    public T findById(int id);
    public void delete(T t);
    public void update(T t);

}
