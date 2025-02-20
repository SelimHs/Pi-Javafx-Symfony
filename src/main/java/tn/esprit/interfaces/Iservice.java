package tn.esprit.interfaces;

import java.util.List;

public interface Iservice<T>  {
    public void add(T t);
    public List<T> getAll();
<<<<<<< HEAD
=======

    void delete(int id);

>>>>>>> c026506 ( integration comp)
    public T findById(int id);
    public void delete(T t);
    public void update(T t);

}
