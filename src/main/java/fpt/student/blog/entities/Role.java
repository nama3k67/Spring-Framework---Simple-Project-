package fpt.student.blog.entities;

import lombok.Data;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "roles")
@Data
public class Role {
    @Id
    @GeneratedValue
    private int id;

    @Column(unique = true, nullable = false)
    @Nationalized
    private String name;

    @OneToMany(mappedBy = "role", targetEntity = Users.class)
    private Set<Users> users;
}
