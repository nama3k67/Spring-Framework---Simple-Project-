package fpt.student.blog.services.impl;

import fpt.student.blog.entities.VerificationToken;
import fpt.student.blog.repositories.VerificationTokenRepository;
import fpt.student.blog.services.VerificationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VerificationTokenServiceImpl implements VerificationTokenService {
    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    @Override
    public Optional<VerificationToken> findByToken(String token) {
        return verificationTokenRepository.findByToken(token);
    }

    @Override
    public <S extends VerificationToken> S save(S s) {
        return verificationTokenRepository.save(s);
    }

    @Override
    public <S extends VerificationToken> Iterable<S> saveAll(Iterable<S> iterable) {
        return verificationTokenRepository.saveAll(iterable);
    }

    @Override
    public Optional<VerificationToken> findById(Long aLong) {
        return verificationTokenRepository.findById(aLong);
    }

    @Override
    public boolean existsById(Long aLong) {
        return verificationTokenRepository.existsById(aLong);
    }

    @Override
    public Iterable<VerificationToken> findAll() {
        return verificationTokenRepository.findAll();
    }

    @Override
    public Iterable<VerificationToken> findAllById(Iterable<Long> iterable) {
        return verificationTokenRepository.findAllById(iterable);
    }

    @Override
    public long count() {
        return verificationTokenRepository.count();
    }

    @Override
    public void deleteById(Long aLong) {
        verificationTokenRepository.deleteById(aLong);
    }

    @Override
    public void delete(VerificationToken verificationToken) {
        verificationTokenRepository.delete(verificationToken);
    }

    @Override
    public void deleteAll(Iterable<? extends VerificationToken> iterable) {
        verificationTokenRepository.deleteAll(iterable);
    }

    @Override
    public void deleteAll() {
        verificationTokenRepository.deleteAll();
    }
}
