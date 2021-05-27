package com.fastcampus.java.controller;

import com.fastcampus.java.model.SearchParam;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api") // Localhost:8080/api
public class PostController {

    // POST 발생할때란?
    // HTML <Form>
    // ajax 검색
    // http post body -> data
    // json, xml, multipart-form / text-plain


//    @RequestMapping(method = RequestMethod.POST, path = "/postMethod")
    @PostMapping("/postMethod") // 위와 동일한 동작 - PostMapping을 시킬거다. path는 /postMethod
    public SearchParam postMethod(@RequestBody SearchParam searchParam){ // RequestBody에 이러한 값들을 매칭시켜달란 뜻
        return searchParam;
    }

//    @PutMapping("/putMethod")
//    public void put(){
//
//    }

//    @PatchMapping("/patchMethod")
//    public void patch(){
//
//    }

}
