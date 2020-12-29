package fpt.student.blog.services.impl;

import fpt.student.blog.entities.Users;
import fpt.student.blog.entities.VerificationToken;
import fpt.student.blog.repositories.UserRepository;
import fpt.student.blog.services.UserService;
import fpt.student.blog.services.VerificationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private VerificationTokenService verificationTokenService;

    private Date calculateExpiryDate() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(cal.getTime().getTime()));
        cal.add(Calendar.MINUTE, VerificationToken.EXPIRATION);
        return new Date(cal.getTime().getTime());
    }

    @Override
    public String createNewToken(Users user) {
        String token = UUID.randomUUID().toString();
        VerificationToken newToken = new VerificationToken();
        newToken.setToken(token);
        newToken.setUser(user);
        newToken.setExpiryDate(calculateExpiryDate());

        verificationTokenService.save(newToken);
        return token;
    }

    @Override
    public void confirmRegistration(Users user) {
        String token = createNewToken(user);

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(user.getEmail());
        msg.setSubject("Registration Confirmation");
        msg.setText("Hello,\n" +
                "\n" +
                "You have received this email to confirm your email\n" +
                "\n" +
                "To complete your confirmation, please click the link below. The link will automatically expire if not used within 24h and your account will be delete after 10 days if not resend new confirm.\n" +
                "After clicking the link, your verification status will be confirmed and you will return to the site.\n" + "http://localhost:8080/BlogIT/registrationConfirm?token=" + token + "\nGoodbye");

        javaMailSender.send(msg);
    }

    @Scheduled(initialDelay = 100 * 1000, fixedDelay = 10*1000*60)
    public void checkUserExpired(){
        System.out.println("Check expired days of account(10 days after expired day of token):");
        List<Users> users = ((List<Users>)findAll());

        users = users.stream().filter(user -> !user.isEnabled()).collect(Collectors.toList());

        Calendar calendar = Calendar.getInstance();

        users = users.stream()
                .filter(user -> calendar.getTime().getTime() - user.getToken().getExpiryDate().getTime() > TimeUnit.DAYS.toMillis(10))
                .collect(Collectors.toList());
        System.out.println("Delete "+ users.size()+ " users.");
        users.forEach(user -> {
            System.out.println("Delete User : " + user.getName());
            delete(user);
        });
    }

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
