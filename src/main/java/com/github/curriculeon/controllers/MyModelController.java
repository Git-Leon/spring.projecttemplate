package com.github.curriculeon.controllers;

import com.github.curriculeon.repositories.MyModelRepository;
import com.github.curriculeon.services.MyModelService;
import com.github.curriculeon.models.MyModel;
import com.github.curriculeon.utils.controllers.AbstractSimpleController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/my-controller")
public class MyModelController extends AbstractSimpleController<Long, MyModel, MyModelRepository, MyModelService> {
    public MyModelController(MyModelService service) {
        super(service);
    }
}
