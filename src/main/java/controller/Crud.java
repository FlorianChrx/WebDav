package controller;

import java.util.Collection;
import java.util.Optional;

public interface Crud<K, V> {

    Optional<V> findById(K key);

    Collection<V> findAll();

    boolean save(V value);

    boolean modify(K key, V value);

    boolean delete(K key);
}
