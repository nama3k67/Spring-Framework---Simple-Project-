package fpt.student.blog.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
public class Users {
    private int id;
    private String name;
    private String email;
    private String password;
    private Role role;
    private boolean enabled = false;
    private Set<Blogs> blogs;
    private VerificationToken token;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "Email", length = 128, unique = true)
    public String getEmail() {
        return email;
    }


    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "enabled")
    public boolean isEnabled(){return enabled;}

    @ManyToOne(targetEntity = Role.class)
    @JoinColumn(name = "role_id")
    public Role getRole() {
        return role;
    }

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    public Set<Blogs> getBlogs(){return blogs;}

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    public VerificationToken getToken(){return token;}

}
