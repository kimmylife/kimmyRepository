package com.koreait.day1.service;

import com.koreait.day1.model.entity.TbExamBook;
import com.koreait.day1.model.network.Header;
import com.koreait.day1.model.network.Pagination;
import com.koreait.day1.model.network.request.TbExamBookRequest;
import com.koreait.day1.model.network.response.TbExamBookResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TbExamBookApiLogicService extends BaseService <TbExamBookRequest, TbExamBookResponse, TbExamBook> {


    private TbExamBookResponse response(TbExamBook tbExamBook){
        TbExamBookResponse tbExamBookResponse = TbExamBookResponse.builder()
                .bkUid(tbExamBook.getBkUid())
                .bkTitle(tbExamBook.getBkTitle())
                .bkSummary(tbExamBook.getBkSummary())
                .bkPrice(tbExamBook.getBkPrice())
                .bkViewcnt(tbExamBook.getBkViewcnt())
                .bkRegdate(tbExamBook.getBkRegdate())
                .build();
        return tbExamBookResponse;
    }


    @Override
    public Header<TbExamBookResponse> create(Header<TbExamBookRequest> request) {
        TbExamBookRequest tbExamBookRequest = request.getData();
        TbExamBook tbExamBook = TbExamBook.builder()
                .bkTitle(tbExamBookRequest.getBkTitle())
                .bkSummary(tbExamBookRequest.getBkSummary())
                .bkPrice(tbExamBookRequest.getBkPrice())
                .bkViewcnt(0)
                .bkRegdate(LocalDateTime.now())
                .build();
        TbExamBook newTbExamBook = baseRepository.save(tbExamBook);
        return Header.OK(response(newTbExamBook));
    }

    @Override
    public Header<TbExamBookResponse> read(Long idx) {
        Optional<TbExamBook> tbExamBook = baseRepository.findById(idx);
        return tbExamBook.map(
                        newTbExamBook -> {
                            newTbExamBook.setBkViewcnt(newTbExamBook.getBkViewcnt()+1);
                            return newTbExamBook;
                        }).map(newTbExamBook -> baseRepository.save(newTbExamBook))
                .map(newTbExamBook -> response(newTbExamBook))
                .map(Header::OK).orElseGet(() -> Header.ERROR("데이터 없음"));

    }

    @Override
    public Header<TbExamBookResponse> update(Header<TbExamBookRequest> request) {
        TbExamBookRequest tbExamBookRequest = request.getData();
        Optional<TbExamBook> tbExamBook = baseRepository.findById(tbExamBookRequest.getBkUid());

        return tbExamBook.map(
                newTbExamBook -> {
                    newTbExamBook.setBkTitle(tbExamBookRequest.getBkTitle());
                    newTbExamBook.setBkSummary(tbExamBookRequest.getBkSummary());
                    newTbExamBook.setBkPrice(tbExamBookRequest.getBkPrice());
                    return newTbExamBook;
                }).map(newTbExamBook-> baseRepository.save(newTbExamBook))
                .map(newTbExamBook -> response(newTbExamBook))
                .map(Header::OK).orElseGet(()->Header.ERROR("데이터없음"));
    }

    @Override
    public Header delete(Long idx) {
        Optional<TbExamBook> tbExamBook =baseRepository.findById(idx);

        return tbExamBook.map(newTbExamBook -> {
            baseRepository.delete(newTbExamBook);
            return Header.OK();
        }).orElseGet(() -> Header.ERROR("데이터없음"));
    }

    public Header<List<TbExamBookResponse>> search(Pageable pageable){
        Page<TbExamBook> tbExamBook = baseRepository.findAll(pageable);
        List<TbExamBookResponse> tbExamBookResponseList = tbExamBook.stream().map(
                newTbExamBook -> response(newTbExamBook)).collect(Collectors.toList());

        Pagination pagination = Pagination.builder()
                .totalPages(tbExamBook.getTotalPages())
                .totalElements(tbExamBook.getTotalElements())
                .currentPages(tbExamBook.getNumber())
                .currentElements(tbExamBook.getNumberOfElements())
                .build();
        return Header.OK(tbExamBookResponseList,pagination);
    };




}
