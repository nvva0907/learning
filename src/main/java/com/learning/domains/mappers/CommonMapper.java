package com.learning.domains.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public abstract class CommonMapper {

    @Named("CONVERT_TIMESTAMP_TO_STRING")
    String convertObjectIdToString(Timestamp timestamp) {
        return timestamp.toString();
    }

    @Named("CONVERT_STRING_TO_LIST")
    List<String> convertStringToList(String string) {
        String[] strings = string.split(",");
        return Arrays.asList(strings);
    }

    @Named("CONVERT_LIST_TO_STRING")
    String convertListToString(List<String> strings) {
        String string = strings.toString().trim().replace("]","").replace("[","");
        return string;
    }

    @Named("CONVERT_DATE_TO_STRING")
    String convertDateToString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(date);
    }

    @Named("CONVERT_TIMESTAMP_TO_LONG")
    Long convertTimestampToLong(Timestamp timestamp) {
        return timestamp.getTime();
    }
}
