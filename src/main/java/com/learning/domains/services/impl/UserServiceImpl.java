package com.learning.domains.services.impl;

import com.learning.apps.dtos.PageCustomDTO;
import com.learning.apps.dtos.UserSignUpDTO;
import com.learning.apps.exceptions.BadRequestException;
import com.learning.apps.responses.CustomResponse;
import com.learning.domains.constants.Constant;
import com.learning.domains.constants.ErrorCode;
import com.learning.domains.entities.User;
import com.learning.domains.entities.UserDocument;
import com.learning.domains.mappers.document_mapper.UserMapper;
import com.learning.domains.repositories.UserRepository;
import com.learning.domains.repositories.elastic_search_repository.UserDocumentRepository;
import com.learning.domains.security_config.CustomUserDetails;
import com.learning.domains.services.UserService;
import com.learning.domains.utils.ErrorCodeUtils;
import com.learning.domains.utils.PageUtils;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Resource
    UserRepository userRepository;

    @Resource
    PasswordEncoder passwordEncoder;

    @Resource
    UserDocumentRepository userDocumentRepository;

    @Resource
    UserMapper userMapper;

    @Resource
    PageUtils pageUtils;

    @Override
    public CustomResponse<?> signUp(UserSignUpDTO dto) {
        checkExistUser(dto);
        User newUser = new User();
        newUser.setFullName(dto.getFullName());
        newUser.setUsername(dto.getUsername());
        newUser.setEmail(dto.getEmail());
        newUser.setPassword(passwordEncoder.encode(dto.getPassword()));
        newUser.setPhoneNumber(dto.getPhoneNumber());
        newUser.setImage(dto.getImage());
        newUser.setRoles(Constant.USER_ROLE.USER);
        newUser.setStatus(Constant.USER_STATUS.IN_ACTIVE);
        newUser.setCreatedDate(new Timestamp(new Date().getTime()));
        newUser.setCreatedBy(dto.getUsername());
        // send mail to active account
        userRepository.save(newUser);
        // save into elastic search
        userDocumentRepository.save(mapUser(newUser));
        return CustomResponse.ok(null);
    }

    private UserDocument mapUser(User user) {
        UserDocument userDocument = userMapper.toDTO(user);
        userDocument.setRoles(Arrays.asList(user.getRoles().trim().split(",")));
        return userDocument;
    }

    @Override
    public int getAllSize() {
        return userRepository.findAll().size();
    }

    @Override
    public CustomResponse<?> fullTextSearch(Integer page, Integer size, String quickSearch) {
        QueryBuilder exactMatchQuery = QueryBuilders.boolQuery()
                .should(QueryBuilders.termQuery("fullName.keyword", quickSearch))
                .should(QueryBuilders.termQuery("username.keyword", quickSearch))
                .should(QueryBuilders.termQuery("phoneNumber.keyword", quickSearch))
                .should(QueryBuilders.termQuery("email.keyword", quickSearch));

        QueryBuilder fuzzyMatchQuery = QueryBuilders.boolQuery()
                .should(QueryBuilders.fuzzyQuery("fullName", quickSearch))
                .should(QueryBuilders.fuzzyQuery("username", quickSearch))
                .should(QueryBuilders.fuzzyQuery("phoneNumber", quickSearch))
                .should(QueryBuilders.fuzzyQuery("email", quickSearch));

        QueryBuilder combinedQuery = QueryBuilders.boolQuery()
                .must(exactMatchQuery)
                .should(fuzzyMatchQuery);

        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(combinedQuery).build();
        Page<UserDocument> userDocumentPage = userDocumentRepository.search(searchQuery);
        PageCustomDTO<UserDocument> response = pageUtils.getPage(userDocumentPage.getContent(), page, size, userDocumentPage.getTotalElements(), userDocumentPage.getTotalPages());
        return CustomResponse.ok(response);
    }

    private void checkExistUser(UserSignUpDTO dto) {
        User userExistByUsername = userRepository.findByUsername(dto.getUsername());
        if (!ObjectUtils.isEmpty(userExistByUsername)) {
            throw new BadRequestException(ErrorCodeUtils.getErrorMessage(ErrorCode.USERNAME_ALREADY_USED));
        }
        User userExistByEmail = userRepository.findByEmail(dto.getEmail());
        if (!ObjectUtils.isEmpty(userExistByEmail)) {
            throw new BadRequestException(ErrorCodeUtils.getErrorMessage(ErrorCode.EMAIL_ALREADY_USED));
        }
        User userExistByPhoneNumber = userRepository.findByPhoneNumber(dto.getPhoneNumber());
        if (!ObjectUtils.isEmpty(userExistByPhoneNumber)) {
            throw new BadRequestException(ErrorCodeUtils.getErrorMessage(ErrorCode.PHONE_NUMBER_ALREADY_USED));
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserDetails(user);
    }
}
