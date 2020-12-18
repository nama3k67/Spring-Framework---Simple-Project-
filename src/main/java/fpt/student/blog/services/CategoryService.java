package fpt.student.blog.services;

import fpt.student.blog.entities.Category;

import java.util.Optional;

public interface CategoryService {
    <S extends Category> S save(S s);

    <S extends Category> Iterable<S> saveAll(Iterable<S> iterable);

    Optional<Category> findById(Integer integer);

    boolean existsById(Integer integer);

    Iterable<Category> findAll();

    Iterable<Category> findAllById(Iterable<Integer> iterable);

    long count();

    void deleteById(Integer integer);

    void delete(Category category);

    void deleteAll(Iterable<? extends Category> iterable);

    void deleteAll();
}
