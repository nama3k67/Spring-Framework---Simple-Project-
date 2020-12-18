package fpt.student.blog.services.impl;

import fpt.student.blog.entities.Blogs;
import fpt.student.blog.repositories.BLogRepository;
import fpt.student.blog.services.BLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class BlogServiceImpl implements BLogService {
    @Autowired
    private BLogRepository bLogRepository;

    @Override
    public List<Blogs> findAll() {
        return bLogRepository.findAll();
    }

    @Override
    public List<Blogs> findAll(Sort sort) {
        return bLogRepository.findAll(sort);
    }

    @Override
    public List<Blogs> findAllById(Iterable<Integer> iterable) {
        return bLogRepository.findAllById(iterable);
    }

    @Override
    public <S extends Blogs> List<S> saveAll(Iterable<S> iterable) {
        return bLogRepository.saveAll(iterable);
    }

    @Override
    public void flush() {
        bLogRepository.flush();
    }

    @Override
    public <S extends Blogs> S saveAndFlush(S s) {
        return bLogRepository.saveAndFlush(s);
    }

    @Override
    public void deleteInBatch(Iterable<Blogs> iterable) {
        bLogRepository.deleteInBatch(iterable);
    }

    @Override
    public void deleteAllInBatch() {
        bLogRepository.deleteAllInBatch();
    }

    @Override
    public Blogs getOne(Integer integer) {
        return bLogRepository.getOne(integer);
    }

    @Override
    public <S extends Blogs> List<S> findAll(Example<S> example) {
        return bLogRepository.findAll(example);
    }

    @Override
    public <S extends Blogs> List<S> findAll(Example<S> example, Sort sort) {
        return bLogRepository.findAll(example, sort);
    }

    @Override
    public Page<Blogs> findAll(Pageable pageable) {
        return bLogRepository.findAll(pageable);
    }

    @Override
    public <S extends Blogs> S save(S s) {
        return bLogRepository.save(s);
    }

    @Override
    public Optional<Blogs> findById(Integer integer) {
        return bLogRepository.findById(integer);
    }

    @Override
    public boolean existsById(Integer integer) {
        return bLogRepository.existsById(integer);
    }

    @Override
    public long count() {
        return bLogRepository.count();
    }

    @Override
    public void deleteById(Integer integer) {
        bLogRepository.deleteById(integer);
    }

    @Override
    public void delete(Blogs blogs) {
        bLogRepository.delete(blogs);
    }

    @Override
    public void deleteAll(Iterable<? extends Blogs> iterable) {
        bLogRepository.deleteAll(iterable);
    }

    @Override
    public void deleteAll() {
        bLogRepository.deleteAll();
    }

    @Override
    public <S extends Blogs> Optional<S> findOne(Example<S> example) {
        return bLogRepository.findOne(example);
    }

    @Override
    public <S extends Blogs> Page<S> findAll(Example<S> example, Pageable pageable) {
        return bLogRepository.findAll(example, pageable);
    }

    @Override
    public <S extends Blogs> long count(Example<S> example) {
        return bLogRepository.count(example);
    }

    @Override
    public <S extends Blogs> boolean exists(Example<S> example) {
        return bLogRepository.exists(example);
    }

    @Override
    public List<Blogs> findByCateId(int cateId) {
        return bLogRepository.findByCateId(cateId);
    }
}
