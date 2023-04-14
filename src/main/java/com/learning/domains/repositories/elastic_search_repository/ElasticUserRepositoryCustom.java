package com.learning.domains.repositories.elastic_search_repository;

import com.learning.domains.entities.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.repository.NoRepositoryBean;

public interface ElasticUserRepositoryCustom extends ElasticsearchRepository<User, Long> {
    User findByUsername(String name);
}
