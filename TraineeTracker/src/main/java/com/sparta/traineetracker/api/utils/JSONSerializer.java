package com.sparta.traineetracker.api.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.traineetracker.api.dtos.TrackerDTO;
import com.sparta.traineetracker.entities.User;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

import java.util.List;

public class JSONSerializer {

    private static ObjectMapper mapper = new ObjectMapper();

    public static String serialiseEntityList(List<EntityModel> list) {
        try {
            return mapper.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String serialiseUser(User user) {
        try {
            return mapper.writeValueAsString(user);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String serialiseCollection(CollectionModel<TrackerDTO> collectionModel) {

        try {
            return mapper.writeValueAsString(collectionModel);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


}
