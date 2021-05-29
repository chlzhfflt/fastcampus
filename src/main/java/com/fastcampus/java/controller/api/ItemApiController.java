package com.fastcampus.java.controller.api;

import com.fastcampus.java.controller.CrudController;
import com.fastcampus.java.ifs.CrudInterface;
import com.fastcampus.java.model.entity.Item;
import com.fastcampus.java.model.network.Header;
import com.fastcampus.java.model.network.request.ItemApiRequest;
import com.fastcampus.java.model.network.response.ItemApiResponse;
import com.fastcampus.java.service.ItemApiLogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/api/item")
public class ItemApiController extends CrudController<ItemApiRequest, ItemApiResponse, Item> {

}

// Controller 와 Service가 여러개 있다면 or Controller 생성
// Controller마다 Entity를 추가해주고 or Req , Res, Entity 지정
// CrudController를 상속받음
// Service에서 Entity 추가, BaseService를 상속받음