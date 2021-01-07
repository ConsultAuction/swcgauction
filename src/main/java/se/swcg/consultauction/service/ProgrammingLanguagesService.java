package se.swcg.consultauction.service;

import se.swcg.consultauction.dto.ProgrammingLanguagesDto;

import java.util.List;

public interface ProgrammingLanguagesService {

    List<ProgrammingLanguagesDto> findAll();
    ProgrammingLanguagesDto findById(String prePopLangId);

    ProgrammingLanguagesDto create(ProgrammingLanguagesDto dto);
    ProgrammingLanguagesDto update(ProgrammingLanguagesDto dto);
    boolean  delete (String prePopLangId);
}
