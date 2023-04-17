package com.learning.domains.repositories.elastic_search_repository;

import com.learning.domains.entities.ElasticUser;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ElasticUserRepositoryCustom extends ElasticsearchRepository<ElasticUser, Long> {
    ElasticUser findByPhoneNumber(String name);
}
