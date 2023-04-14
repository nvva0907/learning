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
@Document(indexName = "user_index")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long id;

    @Column(name = "full_name")
    @Field(type = FieldType.Text, name = "fullName")
    private String fullName;

    @Column(name = "image")
    @Field(type = FieldType.Text, name = "image")
    private String image;

    @Column(name = "email")
    @Field(type = FieldType.Text, name = "email")
    private String email;

    @Column(name = "password")
    @Field(type = FieldType.Text, name = "password")
    private String password;

    @Column(name = "phone_number")
    @Field(type = FieldType.Text, name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "roles")
    @Field(type = FieldType.Text, name = "roles")
    private String roles;

    @Column(name = "status")
    @Field(type = FieldType.Text, name = "status")
    private String status;

    @Column(name = "created_date")
    @Field(type = FieldType.Date, name = "createdDate")
    private Timestamp createdDate;

    @Column(name = "modified_date")
    @Field(type = FieldType.Date, name = "modifiedDate")
    private Timestamp modifiedDate;

    @Column(name = "created_by")
    @Field(type = FieldType.Text, name = "createdBy")
    private String createdBy;

    @Column(name = "modified_by")
    @Field(type = FieldType.Text, name = "modifiedBy")
    private String modifiedBy;

    @Column(name = "username")
    @Field(type = FieldType.Text, name = "username")
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
