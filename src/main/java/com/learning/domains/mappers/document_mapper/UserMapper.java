package com.learning.domains.mappers.document_mapper;

import com.learning.domains.entities.User;
import com.learning.domains.entities.UserDocument;
import com.learning.domains.mappers.BaseMapper;
import com.learning.domains.mappers.CommonMapper;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CommonMapper.class}, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public abstract class UserMapper implements BaseMapper<UserDocument, User> {
    @Override
    @Mappings(
            @Mapping(source = "roles", target = "roles", ignore = true)
    )
    @InheritConfiguration
    @Named("userDocumentToEntity")
    public abstract User toEntity(UserDocument dto);

    @Override
    @IterableMapping(qualifiedByName = "userDocumentToEntity")
    public abstract List<User> toListEntity(List<UserDocument> dtoList);

    @Override
    @Mappings(
            @Mapping(source = "roles", target = "roles", ignore = true)
    )
    @InheritConfiguration
    @Named("userToUserDocument")
    public abstract UserDocument toDTO(User entity);


    @Override
    @IterableMapping(qualifiedByName = "userToUserDocument")
    public abstract List<UserDocument> toListDTO(List<User> entityList);
}
