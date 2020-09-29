package com.github.curriculeon.utils;

/**
 * @author leonhunter
 * @created 04/09/2020 - 9:28 PM
 */
import com.github.curriculeon.utils.controllers.SimpleControllerInterface;
import com.github.curriculeon.utils.services.EntityInterface;
import com.github.curriculeon.utils.services.SimpleServiceInterface;
import org.junit.Assert;
import org.mockito.BDDMockito;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;
import java.util.function.Supplier;

/**
 * @author leonhunter
 * @created 04/04/2020 - 12:25 AM
 */
public interface ServiceTestInterface<
        IdType extends Serializable,
        EntityType extends EntityInterface<IdType>,
        CrudRepositoryType extends CrudRepository<EntityType, IdType>,
        ServiceType extends SimpleServiceInterface<IdType, EntityType, CrudRepositoryType>,
        ControllerType extends SimpleControllerInterface<IdType, EntityType, CrudRepositoryType, ServiceType>> {
    ServiceType getService();

    ControllerType getController();

    // given
    default void test(Supplier<EntityType> entityTypeSupplier) {
        EntityType expectedMyObject = entityTypeSupplier.get();
        HttpStatus expected = HttpStatus.CREATED;
        BDDMockito
                .given(getService().create(expectedMyObject))
                .willReturn(expectedMyObject);

        // When
        ResponseEntity<EntityType> response = getController().create(expectedMyObject);
        HttpStatus actual = response.getStatusCode();
        EntityType actualMyObject = response.getBody();

        // Then
        Assert.assertEquals(expected, actual);
        Assert.assertEquals(expectedMyObject, actualMyObject);
    }

}
