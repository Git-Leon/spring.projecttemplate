package com.github.curriculeon.services;


import com.github.curriculeon.MyApplication;

import com.github.curriculeon.controllers.MyModelController;
import com.github.curriculeon.models.MyModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = MyApplication.class)
public class MyModelServiceTest {
    @MockBean
    private MyModelService service;

    private MyModelController controller;

    @Before
    public void setup(){
        this.controller = new MyModelController(service);
    }


    @Test
    public void testCreate() {
        // Given
        HttpStatus expected = HttpStatus.CREATED;
        MyModel expectedMyModel = new MyModel();
        BDDMockito
                .given(service.create(expectedMyModel))
                .willReturn(expectedMyModel);

        // When
        ResponseEntity<MyModel> response = controller.create(expectedMyModel);
        HttpStatus actual = response.getStatusCode();
        MyModel actualMyModel = response.getBody();

        // Then
        Assert.assertEquals(expected, actual);
        Assert.assertEquals(expectedMyModel, actualMyModel);
    }


    @Test
    public void testShow() {
        // Given
        Long expectedId = 1L;
        HttpStatus expected = HttpStatus.OK;
        MyModel expectedMyModel = new MyModel();
        expectedMyModel.setId(expectedId);
        BDDMockito.
                given(service.findById(1L))
                .willReturn(expectedMyModel);

        // When
        ResponseEntity<MyModel> response = controller.show(expectedId);
        HttpStatus actual = response.getStatusCode();
        MyModel actualMyModel = response.getBody();

        // Then
        Assert.assertEquals(expected, actual);
        Assert.assertEquals(expectedMyModel, actualMyModel);
    }

}
