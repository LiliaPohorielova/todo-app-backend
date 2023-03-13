package ua.nure.todo.facade;

import ua.nure.todo.web.dto.request.RequestDto;
import ua.nure.todo.web.dto.response.ResponseDto;

import java.util.List;

public interface BaseFacade<REQ extends RequestDto, RES extends ResponseDto> {

    RES create(REQ req);
    RES update(REQ req, Long id);
    void delete(Long id);
    RES findById(Long id);
    List<RES> findAll();
}
