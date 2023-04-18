package com.learning.domains.utils;

import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;


@Component
public class ElasticSearchUtils {

    public <E, R extends ElasticsearchRepository<E, Long>> Page<E> quickSearch(Pageable pageable, String quickSearch, R repository, String... fieldToSearch) {
        if (ObjectUtils.isEmpty(quickSearch)) {
            return repository.findAll(pageable);
        }
        QueryBuilder exactMatchQuery = QueryBuilders.multiMatchQuery(quickSearch, fieldToSearch)
                .operator(Operator.OR);
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(exactMatchQuery).build();
        Page<E> listEntity = repository.search(searchQuery);
        if (!ObjectUtils.isEmpty(listEntity.getContent()) && listEntity.getContent().size() == 1) {
            return listEntity;
        } else {
            QueryBuilder fuzzinessMatchQuery = QueryBuilders.multiMatchQuery(quickSearch, fieldToSearch)
                    .fuzziness(Fuzziness.AUTO)
                    .operator(Operator.OR);
            NativeSearchQuery fuzzyQuery = new NativeSearchQueryBuilder().withQuery(fuzzinessMatchQuery).build();
            return repository.search(fuzzyQuery);
        }
    }
}
