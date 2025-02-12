package tn.esprit.interfaces;

import java.util.List;
import java.util.Optional;

public interface Iservice<T> {
    void add(T t);
    List<T> getAll();
    Optional<T> findById(int id);
    void delete(int id);
    void update(T t);
}
