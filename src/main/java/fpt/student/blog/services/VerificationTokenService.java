package fpt.student.blog.services;

import fpt.student.blog.entities.VerificationToken;

import java.util.Optional;

public interface VerificationTokenService {
    Optional<VerificationToken> findByToken(String token);

    <S extends VerificationToken> S save(S s);

    <S extends VerificationToken> Iterable<S> saveAll(Iterable<S> iterable);

    Optional<VerificationToken> findById(Long aLong);

    boolean existsById(Long aLong);

    Iterable<VerificationToken> findAll();

    Iterable<VerificationToken> findAllById(Iterable<Long> iterable);

    long count();

    void deleteById(Long aLong);

    void delete(VerificationToken verificationToken);

    void deleteAll(Iterable<? extends VerificationToken> iterable);

    void deleteAll();
}
