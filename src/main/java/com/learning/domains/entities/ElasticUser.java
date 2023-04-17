package com.learning.domains.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.sql.Timestamp;

@Document(indexName = "learning", type = "elastic-user", refreshInterval = "-1")
@Getter
@Setter
@NoArgsConstructor
public class ElasticUser {
    @Id
    public Long id;
    @Field(type = FieldType.Text, name = "fullName")
    private String fullName;
    @Field(type = FieldType.Text, name = "image")
    private String image;
    @Field(type = FieldType.Text, name = "email")
    private String email;
    @Field(type = FieldType.Text, name = "password")
    private String password;
    @Field(type = FieldType.Text, name = "phoneNumber")
    private String phoneNumber;
    @Field(type = FieldType.Text, name = "roles")
    private String roles;
    @Field(type = FieldType.Text, name = "status")
    private String status;
    @Field(type = FieldType.Date, name = "createdDate")
    private Timestamp createdDate;
    @Field(type = FieldType.Date, name = "modifiedDate")
    private Timestamp modifiedDate;
    @Field(type = FieldType.Text, name = "createdBy")
    private String createdBy;
    @Field(type = FieldType.Text, name = "modifiedBy")
    private String modifiedBy;
    @Field(type = FieldType.Text, name = "username")
    private String username;

    public ElasticUser(Long id, String fullName, String image, String email, String password, String phoneNumber, String roles, String status, Timestamp createdDate, Timestamp modifiedDate, String createdBy, String modifiedBy, String username) {
        this.id = id;
        this.fullName = fullName;
        this.image = image;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.roles = roles;
        this.status = status;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.createdBy = createdBy;
        this.modifiedBy = modifiedBy;
        this.username = username;
    }
}
