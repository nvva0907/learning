package com.learning.domains.entities;

import lombok.*;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Table(name = "user")
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(indexName = "learning", type = "user", refreshInterval = "-1")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long id;

    @org.springframework.data.annotation.Id
    private Long esId;

    @Column(name = "full_name")
    @Field(type = FieldType.Text, name = "fullName")
    private String fullName;

    @Column(name = "image")
    private String image;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "roles")
    private String roles;

    @Column(name = "status")
    private String status;

    @Column(name = "created_date")
    private Timestamp createdDate;

    @Column(name = "modified_date")
    private Timestamp modifiedDate;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "modified_by")
    private String modifiedBy;

    @Column(name = "username")
    private String username;



    public User(Long id, String fullName, String image, String email, String password, String phoneNumber) {
        this.id = id;
        this.fullName = fullName;
        this.image = image;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }
}
