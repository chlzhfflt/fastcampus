package com.fastcampus.java.ifs;

import com.fastcampus.java.model.network.Header;
import com.fastcampus.java.model.network.request.UserApiRequest;

public interface CrudInterface<Req,Res> {

    Header<Res> create(Req request);    // todo request object 추가

    Header<Res> read(Long id);

    Header<Res> update(Req request);

    Header delete(Long id);
}
