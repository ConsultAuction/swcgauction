package se.swcg.consultauction.service;

import se.swcg.consultauction.dto.PrePopLanguagesDto;
import se.swcg.consultauction.entity.PrePopLanguages;

import java.util.List;

public interface PrePopLanguagesDtoConversionService {

    PrePopLanguages dtoToPrePopLang(PrePopLanguagesDto dto);
    PrePopLanguagesDto prePopLangToDto(PrePopLanguages prePopLanguages);
    List<PrePopLanguagesDto> prePopLangToDto(List<PrePopLanguages> languages);
}
