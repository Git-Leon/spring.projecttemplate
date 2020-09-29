package com.github.curriculeon.utils.services;

import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;

/**
 * @author leonhunter
 * @created 03/22/2020 - 12:23 PM
 */
public class SimpleServiceImpl<
        IdType extends Serializable,
        EntityType extends EntityInterface<IdType>,
        CrudRepositoryType extends CrudRepository<EntityType, IdType>>
        extends AbstractSimpleService<IdType, EntityType, CrudRepositoryType> {
    public SimpleServiceImpl(CrudRepositoryType crudRepository) {
        super(crudRepository);
    }

    @Override
    public EntityType update(EntityType existingData, EntityType newEntityData) {
        return null;
    }
}