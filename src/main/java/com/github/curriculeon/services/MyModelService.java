package com.github.curriculeon.services;

import com.github.curriculeon.models.MyModel;
import com.github.curriculeon.repositories.MyModelRepository;
import com.github.curriculeon.utils.services.AbstractSimpleService;
import org.springframework.stereotype.Service;

@Service
public class MyModelService extends AbstractSimpleService<Long, MyModel, MyModelRepository> {
    public MyModelService(MyModelRepository crudRepository) {
        super(crudRepository);
    }

    @Override
    public MyModel update(MyModel existingData, MyModel newEntityData) {
        existingData.setName(newEntityData.getName());
        return getRepository().save(existingData);
    }
}