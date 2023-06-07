package nguyennhathao.example.lap3456.entity;


import jakarta.persistence.*;
import lombok.Data;
import nguyennhathao.example.lap3456.repository.User;
import nguyennhathao.example.lap3456.validator.annotation.ValidUserId;

@Entity
@Table(name = "book")
@Data
public class Book {

    //Properties
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getAuthor() {
//        return author;
//    }
//
//    public void setAuthor(String author) {
//        this.author = author;
//    }
//
//    public Double getPrice() {
//        return price;
//    }
//
//    public void setPrice(Double price) {
//        this.price = price;
//    }
//
//    public String getCategory() {
//        return Category;
//    }
//
//    public void setCategory(String category) {
//        Category = category;
//    }
    @Column(name = "title")
    private String title;
    @Column(name = "author")
    private String author;
    @Column(name = "price")
    private Double price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName = "id")
    @ValidUserId
    private User user;
}

