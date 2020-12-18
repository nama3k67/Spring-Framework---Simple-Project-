package fpt.student.blog.services;

import fpt.student.blog.entities.Users;

import java.util.Optional;

public interface UserService {

    Users findByName(String name);

    <S extends Users> S save(S s);

    <S extends Users> Iterable<S> saveAll(Iterable<S> iterable);

    Optional<Users> findById(Integer integer);

    boolean existsById(Integer integer);

    Iterable<Users> findAll();

    Iterable<Users> findAllById(Iterable<Integer> iterable);

    long count();

    void deleteById(Integer integer);

    void delete(Users users);

    void deleteAll(Iterable<? extends Users> iterable);

    void deleteAll();
}
