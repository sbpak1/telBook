package service;

import dto.TelDto;

import java.util.List;

public interface CrudInterface {
    int InsertData(TelDto dto);

    int UpdateData(TelDto dto);

    int deletedData(int id);

    List<TelDto> getListAll(); // 전체 찾기

    TelDto fidById(int id); // 한개 데이터 찾기

    List<TelDto> serchList(String keyword); // 이름 검색



}
