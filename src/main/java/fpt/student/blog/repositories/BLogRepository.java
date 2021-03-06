package fpt.student.blog.repositories;

import fpt.student.blog.entities.Blogs;
import fpt.student.blog.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BLogRepository extends JpaRepository<Blogs, Integer> {
    List<Blogs> findByCateId(int cateId);
    List<Blogs> findByUsers(Users users);
}
