package com.koreait.day1.controller.api;

import com.koreait.day1.controller.CrudController;
import com.koreait.day1.model.entity.TbExamBook;
import com.koreait.day1.model.network.Header;
import com.koreait.day1.model.network.request.TbExamBookRequest;
import com.koreait.day1.model.network.response.TbExamBookResponse;
import com.koreait.day1.service.TbExamBookApiLogicService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exambook")
@RequiredArgsConstructor

public class TbExamBookController extends CrudController<TbExamBookRequest, TbExamBookResponse, TbExamBook> {

    private final TbExamBookApiLogicService tbExamBookApiLogicService;



    @Override
    @PostMapping("")
    public Header<TbExamBookResponse> create(@RequestBody Header<TbExamBookRequest> request) {
        return tbExamBookApiLogicService.create(request);
    }

    @Override
    @GetMapping("{idx}")
    public Header<TbExamBookResponse> read(@PathVariable(name = "idx") Long idx) {
        return tbExamBookApiLogicService.read(idx);
    }

    @Override
    @PutMapping("")
    public Header<TbExamBookResponse> update(@RequestBody Header<TbExamBookRequest> request) {
        return tbExamBookApiLogicService.update(request);
    }

    @Override
    @DeleteMapping("{idx}")
    public Header<TbExamBookResponse> delete(@PathVariable Long idx) {
        return tbExamBookApiLogicService.delete(idx);
    }


    @GetMapping("")
    public Header<List<TbExamBookResponse>> findAll(@PageableDefault(sort = {"bkUid"}, direction = Sort.Direction.DESC) Pageable pageable){
        return tbExamBookApiLogicService.search(pageable);
    }
}
