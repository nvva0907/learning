package com.learning.domains.repositories.elastic_search_repository;

import com.learning.domains.entities.UserDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface UserDocumentRepository extends ElasticsearchRepository<UserDocument, Long> {
}
