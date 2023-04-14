package com.learning.domains.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.Id;

@Getter
@Setter
@Document(indexName = "learning", type = "test")
public class ElasticTest {

    @Id
    public Long id;

    private String fullName;

    private String image;
}
