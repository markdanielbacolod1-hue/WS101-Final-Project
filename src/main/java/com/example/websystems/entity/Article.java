//Add Article entity with approval status - Jade Francine Atencio
package com.example.websystems.entity;

import jakarta.persistence.*;
import com.example.websystems.entity.Category;


import java.time.LocalDate;

@Entity
@Table(name = "articles")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private Integer articleId;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    private String language;

    @Column(name = "author_id", nullable = false)
    private Integer authorId;

    @Column(name = "category_id")
    private Integer categoryId;

    @Column(name = "date_posted")
    private LocalDate datePosted;

    private String image;
    private String status;

    @ManyToOne
    @JoinColumn(
            name = "author_id",
            referencedColumnName = "user_id",
            insertable = false,
            updatable = false
    )
    private User author;

    @ManyToOne
    @JoinColumn(
            name = "category_id",
            referencedColumnName = "category_id",
            insertable = false,
            updatable = false
    )
    private Category category;

    public Integer getArticleId() { return articleId; }
    public String getTitle() { return title; }
    public String getContent() { return content; }
    public String getLanguage() { return language; }
    public Integer getAuthorId() { return authorId; }
    public Integer getCategoryId() { return categoryId; }
    public LocalDate getDatePosted() { return datePosted; }
    public String getStatus() { return status; }
    public User getAuthor() { return author; }
    public Category getCategory() { return category; }

    public void setTitle(String title) { this.title = title; }
    public void setContent(String content) { this.content = content; }
    public void setLanguage(String language) { this.language = language; }
    public void setAuthorId(Integer authorId) { this.authorId = authorId; }
    public void setCategoryId(Integer categoryId) { this.categoryId = categoryId; }
    public void setDatePosted(LocalDate datePosted) { this.datePosted = datePosted; }
    public void setStatus(String status) { this.status = status; }
}
