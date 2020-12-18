package fpt.student.blog.services;

import fpt.student.blog.entities.Blogs;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface BLogService {
    List<Blogs> findAll();

    List<Blogs> findAll(Sort sort);

    List<Blogs> findAllById(Iterable<Integer> iterable);

    <S extends Blogs> List<S> saveAll(Iterable<S> iterable);

    void flush();

    <S extends Blogs> S saveAndFlush(S s);

    void deleteInBatch(Iterable<Blogs> iterable);

    void deleteAllInBatch();

    Blogs getOne(Integer integer);

    <S extends Blogs> List<S> findAll(Example<S> example);

    <S extends Blogs> List<S> findAll(Example<S> example, Sort sort);

    Page<Blogs> findAll(Pageable pageable);

    <S extends Blogs> S save(S s);

    Optional<Blogs> findById(Integer integer);

    boolean existsById(Integer integer);

    long count();

    void deleteById(Integer integer);

    void delete(Blogs blogs);

    void deleteAll(Iterable<? extends Blogs> iterable);

    void deleteAll();

    <S extends Blogs> Optional<S> findOne(Example<S> example);

    <S extends Blogs> Page<S> findAll(Example<S> example, Pageable pageable);

    abstract <S extends Blogs> long count(Example<S> example);

    <S extends Blogs> boolean exists(Example<S> example);

    List<Blogs> findByCateId(int cateId);
}
