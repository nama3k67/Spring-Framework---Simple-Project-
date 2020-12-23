package fpt.student.blog.services.impl;

import fpt.student.blog.entities.Users;
import fpt.student.blog.repositories.UserRepository;
import fpt.student.blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserSeviceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;


    @Override
    public Users findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Users findByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public <S extends Users> S save(S s) {
        return userRepository.save(s);
    }

    @Override
    public <S extends Users> Iterable<S> saveAll(Iterable<S> iterable) {
        return userRepository.saveAll(iterable);
    }

    @Override
    public Optional<Users> findById(Integer integer) {
        return userRepository.findById(integer);
    }

    @Override
    public boolean existsById(Integer integer) {
        return userRepository.existsById(integer);
    }

    @Override
    public Iterable<Users> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Iterable<Users> findAllById(Iterable<Integer> iterable) {
        return userRepository.findAllById(iterable);
    }

    @Override
    public long count() {
        return userRepository.count();
    }

    @Override
    public void deleteById(Integer integer) {
        userRepository.deleteById(integer);
    }

    @Override
    public void delete(Users users) {
        userRepository.delete(users);
    }

    @Override
    public void deleteAll(Iterable<? extends Users> iterable) {
        userRepository.deleteAll(iterable);
    }

    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }
}
