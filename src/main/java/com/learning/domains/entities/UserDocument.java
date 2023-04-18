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
    @Field(type = FieldType.Keyword, name = "id", analyzer = "standard", store = true)
    public Long id;

    @Field(type = FieldType.Text, name = "fullName", analyzer = "standard",store = true)
    private String fullName;

    @Field(type = FieldType.Text, name = "image", analyzer = "standard",store = true)
    private String image;

    @Field(type = FieldType.Text, name = "email", analyzer = "standard", store = true)
    private String email;

    @Field(type = FieldType.Text, name = "password", analyzer = "standard", store = true)
    private String password;

    @Field(type = FieldType.Text, name = "phoneNumber", analyzer = "standard", store = true)
    private String phoneNumber;

    @Field(type = FieldType.Text, name = "roles", analyzer = "standard", store = true)
    private List<String> roles;

    @Field(type = FieldType.Text, name = "status", analyzer = "standard", store = true)
    private String status;

    @Field(type = FieldType.Date, name = "createdDate", analyzer = "standard", store = true)
    private Timestamp createdDate;

    @Field(type = FieldType.Date, name = "modifiedDate", analyzer = "standard", store = true)
    private Timestamp modifiedDate;

    @Field(type = FieldType.Text, name = "createdBy", analyzer = "standard", store = true)
    private String createdBy;

    @Field(type = FieldType.Text, name = "modifiedBy", analyzer = "standard", store = true)
    private String modifiedBy;

    @Field(type = FieldType.Text, name = "username", analyzer = "standard", store = true)
    private String username;
}
