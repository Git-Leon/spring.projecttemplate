package com.github.curriculeon.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.curriculeon.utils.controllers.SimpleControllerInterface;
import com.github.curriculeon.utils.services.EntityInterface;
import com.github.curriculeon.utils.services.SimpleServiceInterface;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.Serializable;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * @author leonhunter
 * @created 04/07/2020 - 3:02 PM
 */
public interface SimpleControllerTestInterface<
        IdType extends Serializable,
        EntityType extends EntityInterface<IdType>,
        CrudRepositoryType extends CrudRepository<EntityType, IdType>,
        ServiceType extends SimpleServiceInterface<IdType, EntityType, CrudRepositoryType>,
        ControllerType extends SimpleControllerInterface<IdType, EntityType, CrudRepositoryType, ServiceType>> {
    String getUrlTemplate();

    CrudRepositoryType getCrudRepository();

    MockMvc getMockMvc();


    default void testCreate(Supplier<EntityType> entityTypeGenerator) throws Exception {
        EntityType myObject = entityTypeGenerator.get();
        BDDMockito
                .given(getCrudRepository().save(myObject))
                .willReturn(myObject);

        String expectedContent = new ObjectMapper().writeValueAsString(myObject);
        this.getMockMvc().perform(MockMvcRequestBuilders
                .post(getUrlTemplate())
                .content(expectedContent)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().string(expectedContent));
    }


    default void testFindById(IdType givenId, Supplier<EntityType> entityTypeGenerator) throws Exception {
        EntityType myObject = entityTypeGenerator.get();
        BDDMockito
                .given(getCrudRepository().findById(givenId))
                .willReturn(Optional.of(myObject));
        String expectedContent = new ObjectMapper().writeValueAsString(myObject);
        this.getMockMvc().perform(MockMvcRequestBuilders
                .get(getUrlTemplate() + givenId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(expectedContent));
    }
}


