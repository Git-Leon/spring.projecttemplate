package com.github.curriculeon.utils.services;

import java.io.Serializable;

public interface EntityInterface<IdType extends Serializable> {
    IdType getId();
    void setId(IdType id);

}
