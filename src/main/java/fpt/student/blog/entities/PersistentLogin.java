package fpt.student.blog.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "persistent_logins")
@Data
public class PersistentLogin {
    @Id
    @Column(name = "Series", length = 64, nullable = false)
    private String series;

    @Column(name = "Username", length = 64, nullable = false)
    private String userName;

    @Column(name = "Token", length = 64, nullable = false)
    private String token;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Last_Used", nullable = false)
    private Date lastUsed;

}
