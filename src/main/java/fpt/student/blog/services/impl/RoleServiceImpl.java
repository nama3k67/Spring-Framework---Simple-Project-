package fpt.student.blog.services.impl;

import fpt.student.blog.entities.Role;
import fpt.student.blog.repositories.RoleRepository;
import fpt.student.blog.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Optional<Role> findByName(String name) {
        return roleRepository.findByName(name);
    }

    @Override
    public <S extends Role> S save(S s) {
        return roleRepository.save(s);
    }

    @Override
    public <S extends Role> Iterable<S> saveAll(Iterable<S> iterable) {
        return roleRepository.saveAll(iterable);
    }

    @Override
    public Optional<Role> findById(Integer integer) {
        return roleRepository.findById(integer);
    }

    @Override
    public boolean existsById(Integer integer) {
        return roleRepository.existsById(integer);
    }

    @Override
    public Iterable<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Iterable<Role> findAllById(Iterable<Integer> iterable) {
        return roleRepository.findAllById(iterable);
    }

    @Override
    public long count() {
        return roleRepository.count();
    }

    @Override
    public void deleteById(Integer integer) {
        roleRepository.deleteById(integer);
    }

    @Override
    public void delete(Role role) {
        roleRepository.delete(role);
    }

    @Override
    public void deleteAll(Iterable<? extends Role> iterable) {
        roleRepository.deleteAll(iterable);
    }

    @Override
    public void deleteAll() {
        roleRepository.deleteAll();
    }
}
