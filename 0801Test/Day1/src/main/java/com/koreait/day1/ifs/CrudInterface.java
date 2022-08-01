package com.koreait.day1.ifs;

import com.koreait.day1.model.network.Header;

public interface CrudInterface<Req, Res>{
    Header<Res> create(Header<Req> request);
    Header<Res> read(Long idx);
    Header<Res> update(Header<Req> request);
    Header<Res> delete(Long idx);

}
