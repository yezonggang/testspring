package com.example.testspring.utils;

import org.springframework.util.CollectionUtils;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class BeanConverter {

    private static final ModelMapper modelMapper = new ModelMapper();

    public BeanConverter() {
    }

    public static ModelMapper getModelMapper() {
        return modelMapper;
    }

    public static <T> List<T> convert(List<?> list, Class<T> clazz) {
        return CollectionUtils.isEmpty(list) ? Collections.emptyList() : (List)list.stream().map((e) -> {
            return convert(e, clazz);
        }).collect(Collectors.toList());
    }

    public static <T> T convert(Object source, Class<T> targetClass) {
        return getModelMapper().map(source, targetClass);
    }

    static {
        modelMapper.getConfiguration().setFullTypeMatchingRequired(true);
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }
}
