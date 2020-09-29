package com.github.curriculeon.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.curriculeon.utils.services.EntityInterface;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.Entity;
import java.util.function.Supplier;

/**
 * @author leonhunter
 * @created 04/09/2020 - 9:20 PM
 */
public interface EntityInterfaceTestInterface<EntityType extends EntityInterface<?>> {
    @Test
    default void testClassSignatureAnnotations() {
        Assert.assertTrue(
                getEntityGenerator()
                        .get()
                        .getClass()
                        .isAnnotationPresent(Entity.class));
    }

    @Test
    default void testCreateJson() throws JsonProcessingException {
        ObjectMapper jsonWriter = new ObjectMapper();
        EntityType entity = getEntityGenerator().get();
        String json = jsonWriter.writeValueAsString(entity);
    }

    @Test
    default void testEntityInterface() {
        Assert.assertTrue(getEntityGenerator().get() instanceof EntityInterface);
    }

    Supplier<EntityType> getEntityGenerator();
}
