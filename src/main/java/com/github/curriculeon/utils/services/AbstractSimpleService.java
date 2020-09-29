package com.github.curriculeon.utils.services;

import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;

/**
 * @author leonhunter
 * @created 03/09/2020 - 9:22 PM
 */
abstract public class AbstractSimpleService<
        IdType extends Serializable,
        EntityType extends EntityInterface<IdType>,
        CrudRepositoryType extends CrudRepository<EntityType, IdType>>
        implements SimpleServiceInterface<IdType, EntityType, CrudRepositoryType> {
    private final CrudRepositoryType crudRepository;

    public AbstractSimpleService(CrudRepositoryType crudRepository) {
        this.crudRepository = crudRepository;
    }

    @Override
    public CrudRepositoryType getRepository() {
        return crudRepository;
    }
}
