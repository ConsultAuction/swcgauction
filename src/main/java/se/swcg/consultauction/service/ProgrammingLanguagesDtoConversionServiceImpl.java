package se.swcg.consultauction.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.swcg.consultauction.dto.ProgrammingLanguagesDto;
import se.swcg.consultauction.entity.ProgrammingLanguages;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class ProgrammingLanguagesDtoConversionServiceImpl implements ProgrammingLanguagesDtoConversionService {
    @Override
    public ProgrammingLanguages dtoToPrePopLang(ProgrammingLanguagesDto dto) {
        return new ProgrammingLanguages(dto.getLanguagesId(), dto.getLanguage());
    }

    @Override
    public ProgrammingLanguagesDto prePopLangToDto(ProgrammingLanguages programmingLanguages) {
        return new ProgrammingLanguagesDto(programmingLanguages.getLanguagesId(), programmingLanguages.getLanguage());
    }

    @Override
    public List<ProgrammingLanguagesDto> prePopLangToDto(List<ProgrammingLanguages> languages) {

        if (languages == null) {
            languages = new ArrayList<>();
        }

        List<ProgrammingLanguagesDto> languagesDtos = new ArrayList<>();

        for (ProgrammingLanguages language: languages) {
            languagesDtos.add(prePopLangToDto(language));
        }

        return languagesDtos;
    }
}
