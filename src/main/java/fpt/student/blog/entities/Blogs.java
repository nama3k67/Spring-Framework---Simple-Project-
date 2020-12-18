package fpt.student.blog.entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
public class Blogs {
    private int id;
    private String title;
    private String content;
    private String picture;
    private Date dateModify;
    private Integer pageCount;
    private Integer cateId;
    private Category categoryByCateId;
    private Users users;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "picture")
    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Basic
    @Column(name = "date_modify")
    public Date getDateModify() {
        return dateModify;
    }

    public void setDateModify(Date dateModify) {
        this.dateModify = dateModify;
    }

    @Basic
    @Column(name = "page_count")
    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    @Basic
    @Column(name = "cate_Id")
    public Integer getCateId() {
        return cateId;
    }

    public void setCateId(Integer cateId) {
        this.cateId = cateId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Blogs blogs = (Blogs) o;
        return id == blogs.id &&
                Objects.equals(title, blogs.title) &&
                Objects.equals(content, blogs.content) &&
                Objects.equals(picture, blogs.picture) &&
                Objects.equals(dateModify, blogs.dateModify) &&
                Objects.equals(pageCount, blogs.pageCount) &&
                Objects.equals(cateId, blogs.cateId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, picture, dateModify, pageCount, cateId);
    }

    @ManyToOne
    @JoinColumn(name = "cate_Id", referencedColumnName = "id", insertable = false, updatable = false)
    public Category getCategoryByCateId() {
        return categoryByCateId;
    }

    public void setCategoryByCateId(Category categoryByCateId) {
        this.categoryByCateId = categoryByCateId;
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    public Users getUsers(){
        return users;
    }

    public void setUsers(Users users){
        this.users = users;
    }
}
