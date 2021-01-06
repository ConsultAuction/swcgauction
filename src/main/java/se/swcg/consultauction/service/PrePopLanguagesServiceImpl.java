package se.swcg.consultauction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.swcg.consultauction.dto.PrePopLanguagesDto;
import se.swcg.consultauction.entity.PrePopLanguages;
import se.swcg.consultauction.exception.ResourceNotFoundException;
import se.swcg.consultauction.repository.PrePopLanguagesRepository;

import java.util.List;

@Service
public class PrePopLanguagesServiceImpl implements PrePopLanguagesService {

    @Autowired
    PrePopLanguagesRepository repo;

    @Autowired
    PrePopLanguagesDtoConversionService converter;

    @Override
    public List<PrePopLanguagesDto> findAll() {
        List<PrePopLanguages> languages = repo.findAll();

        if (languages.isEmpty()) {
            throw new ResourceNotFoundException("Could not find any languages.");
        }

        return converter.prePopLangToDto(languages);
    }

    @Override
    public PrePopLanguagesDto findById(String prePopLangId) {
        return converter.prePopLangToDto(
                repo.findById(prePopLangId)
                .orElseThrow(() -> new ResourceNotFoundException("Could not find language with id: " + prePopLangId)));

    }

    @Override
    public PrePopLanguagesDto create(PrePopLanguagesDto dto) {
        return converter.prePopLangToDto(repo.save(converter.dtoToPrePopLang(dto)));
    }

    @Override
    public PrePopLanguagesDto update(PrePopLanguagesDto dto) {
        if (dto.getLanguagesId() == null) {
            throw new IllegalArgumentException("Invalid id for PrePopLanguages: update");
        }

        PrePopLanguages foundLanguage = converter.dtoToPrePopLang(findById((dto.getLanguagesId())));
        PrePopLanguages updatedLanguage = converter.dtoToPrePopLang(dto);

        foundLanguage.setLanguage(updatedLanguage.getLanguage());

        return converter.prePopLangToDto(repo.save(foundLanguage));
    }

    @Override
    public boolean delete(String prePopLangId) {
        repo.delete(converter.dtoToPrePopLang(findById(prePopLangId)));

        //Returns true if language is not present
        return !repo.findById(prePopLangId).isPresent();
    }
}
