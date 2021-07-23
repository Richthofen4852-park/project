package com.example.calcapi.ifs;

import com.example.calcapi.model.Header;

public interface CrudInterface<Req, Res> {

    Header<Res> create(Header<Req> req);

    Header<Res> read(Long id);

}
