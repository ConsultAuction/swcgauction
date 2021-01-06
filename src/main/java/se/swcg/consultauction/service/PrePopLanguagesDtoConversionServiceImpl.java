package se.swcg.consultauction.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.swcg.consultauction.dto.PrePopLanguagesDto;
import se.swcg.consultauction.entity.PrePopLanguages;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class PrePopLanguagesDtoConversionServiceImpl implements PrePopLanguagesDtoConversionService{
    @Override
    public PrePopLanguages dtoToPrePopLang(PrePopLanguagesDto dto) {
        return new PrePopLanguages(dto.getLanguagesId(), dto.getLanguage());
    }

    @Override
    public PrePopLanguagesDto prePopLangToDto(PrePopLanguages prePopLanguages) {
        return new PrePopLanguagesDto(prePopLanguages.getLanguagesId(), prePopLanguages.getLanguage());
    }

    @Override
    public List<PrePopLanguagesDto> prePopLangToDto(List<PrePopLanguages> languages) {

        if (languages == null) {
            languages = new ArrayList<>();
        }

        List<PrePopLanguagesDto> languagesDtos = new ArrayList<>();

        for (PrePopLanguages language: languages) {
            languagesDtos.add(prePopLangToDto(language));
        }

        return languagesDtos;
    }
}
