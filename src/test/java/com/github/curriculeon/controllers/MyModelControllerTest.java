package com.github.curriculeon.controllers;

import com.github.curriculeon.models.MyModel;
import com.github.curriculeon.repositories.MyModelRepository;
import com.github.curriculeon.services.MyModelService;
import com.github.curriculeon.utils.SimpleControllerTestInterface;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


/**
 * @author leon on 8/30/18.
 */
@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class MyModelControllerTest implements SimpleControllerTestInterface<Long, MyModel, MyModelRepository, MyModelService, MyModelController> {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private MyModelRepository repository;

    @Test
    public void test() throws Exception {
        testFindById(1L, MyModel::new);
    }

    @Test
    public void testCreate() throws Exception {
        testCreate(MyModel::new);
    }

    @Override
    public String getUrlTemplate() {
        return "/my-controller/";
    }

    @Override
    public MyModelRepository getCrudRepository() {
        return repository;
    }

    @Override
    public MockMvc getMockMvc() {
        return mvc;
    }
}
