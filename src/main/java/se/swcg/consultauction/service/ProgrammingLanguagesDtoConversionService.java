package se.swcg.consultauction.service;

import se.swcg.consultauction.dto.ProgrammingLanguagesDto;
import se.swcg.consultauction.entity.ProgrammingLanguages;

import java.util.List;

public interface ProgrammingLanguagesDtoConversionService {

    ProgrammingLanguages dtoToPrePopLang(ProgrammingLanguagesDto dto);
    ProgrammingLanguagesDto prePopLangToDto(ProgrammingLanguages programmingLanguages);
    List<ProgrammingLanguagesDto> prePopLangToDto(List<ProgrammingLanguages> languages);
}
