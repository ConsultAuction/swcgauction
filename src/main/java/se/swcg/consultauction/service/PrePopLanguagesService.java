package se.swcg.consultauction.service;

import se.swcg.consultauction.dto.PrePopLanguagesDto;

import java.util.List;

public interface PrePopLanguagesService {

    List<PrePopLanguagesDto> findAll();
    PrePopLanguagesDto findById(String prePopLangId);

    PrePopLanguagesDto create(PrePopLanguagesDto dto);
    PrePopLanguagesDto update(PrePopLanguagesDto dto);
    boolean  delete (String prePopLangId);
}
