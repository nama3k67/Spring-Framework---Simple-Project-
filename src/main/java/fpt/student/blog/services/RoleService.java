package fpt.student.blog.services;

import fpt.student.blog.entities.Role;

import java.util.Optional;

public interface RoleService {
    <S extends Role> S save(S s);

    <S extends Role> Iterable<S> saveAll(Iterable<S> iterable);

    Optional<Role> findById(Integer integer);

    boolean existsById(Integer integer);

    Iterable<Role> findAll();

    Iterable<Role> findAllById(Iterable<Integer> iterable);

    long count();

    void deleteById(Integer integer);

    void delete(Role role);

    void deleteAll(Iterable<? extends Role> iterable);

    void deleteAll();
}
