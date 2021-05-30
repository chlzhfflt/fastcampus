package com.fastcampus.java.controller.api;

import com.fastcampus.java.controller.CrudController;
import com.fastcampus.java.model.entity.Settlement;
import com.fastcampus.java.model.network.request.SettlementApiRequest;
import com.fastcampus.java.model.network.response.SettlementApiResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/settlement")
public class SettlementApiController extends CrudController<SettlementApiRequest, SettlementApiResponse, Settlement> {

}
