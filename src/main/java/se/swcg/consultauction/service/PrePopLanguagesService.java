package se.swcg.consultauction.service;

import se.swcg.consultauction.dto.PrePopLanguagesDto;
import se.swcg.consultauction.entity.PrePopLanguages;

import java.util.List;

public interface PrePopLanguagesService {

    List<PrePopLanguages> findAll();
    PrePopLanguages findById(String PrePopLangId);

    PrePopLanguages create(PrePopLanguagesDto dto);
    PrePopLanguages update(PrePopLanguagesDto dto);
    boolean  delete (String PrePopLangId);
}
