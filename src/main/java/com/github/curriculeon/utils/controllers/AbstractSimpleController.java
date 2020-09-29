package com.github.curriculeon.utils.controllers;


import com.github.curriculeon.utils.services.EntityInterface;
import com.github.curriculeon.utils.services.SimpleServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;

/**
 * @author leonhunter
 * @created 03/27/2020 - 5:23 PM
 */
public class AbstractSimpleController<
        IdType extends Serializable,
        EntityType extends EntityInterface<IdType>,
        CrudRepositoryType extends CrudRepository<EntityType, IdType>,
        ServiceType extends SimpleServiceInterface<IdType, EntityType, CrudRepositoryType>>
        implements SimpleControllerInterface<IdType, EntityType, CrudRepositoryType, ServiceType> {
    private ServiceType service;

    @Autowired
    public AbstractSimpleController(ServiceType service) {
        this.service = service;
    }

    @Override
    public ServiceType getService() {
        return service;
    }
}
