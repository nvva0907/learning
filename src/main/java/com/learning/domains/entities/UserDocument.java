package com.learning.domains.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.sql.Timestamp;
import java.util.List;

@Document(indexName = "learning", type = "elastic-user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDocument {
    @Id
    @Field(type = FieldType.Keyword, name = "id", analyzer = "standard")
    public Long id;

    @Field(type = FieldType.Text, name = "fullName", analyzer = "standard")
    private String fullName;

    @Field(type = FieldType.Text, name = "image", analyzer = "standard")
    private String image;

    @Field(type = FieldType.Keyword, name = "email", analyzer = "standard")
    private String email;

    @Field(type = FieldType.Text, name = "password", analyzer = "standard")
    private String password;

    @Field(type = FieldType.Keyword, name = "phoneNumber", analyzer = "standard")
    private String phoneNumber;

    @Field(type = FieldType.Text, name = "roles", analyzer = "standard")
    private List<String> roles;

    @Field(type = FieldType.Text, name = "status", analyzer = "standard")
    private String status;

    @Field(type = FieldType.Date, name = "createdDate", analyzer = "standard")
    private Timestamp createdDate;

    @Field(type = FieldType.Date, name = "modifiedDate", analyzer = "standard")
    private Timestamp modifiedDate;

    @Field(type = FieldType.Text, name = "createdBy", analyzer = "standard")
    private String createdBy;

    @Field(type = FieldType.Text, name = "modifiedBy", analyzer = "standard")
    private String modifiedBy;

    @Field(type = FieldType.Keyword, name = "username", analyzer = "standard")
    private String username;
}
