package se.swcg.consultauction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.swcg.consultauction.dto.ProgrammingLanguagesDto;
import se.swcg.consultauction.entity.ProgrammingLanguages;
import se.swcg.consultauction.exception.ResourceNotFoundException;
import se.swcg.consultauction.repository.ProgrammingLanguagesRepository;

import java.util.List;

@Service
public class ProgrammingLanguagesServiceImpl extends ServiceHelper implements ProgrammingLanguagesService {

    @Autowired
    ProgrammingLanguagesRepository repo;

    @Autowired
    ProgrammingLanguagesDtoConversionService converter;

    @Override
    public List<ProgrammingLanguagesDto> findAll() {

        return checkIfListIsEmpty(converter.prePopLangToDto(repo.findAll()),"Could not find any languages.");
    }

    @Override
    public ProgrammingLanguagesDto findById(String prePopLangId) {
        return converter.prePopLangToDto(
                repo.findById(prePopLangId)
                .orElseThrow(() -> new ResourceNotFoundException("Could not find language with id: " + prePopLangId)));

    }

    @Override
    public ProgrammingLanguagesDto create(ProgrammingLanguagesDto dto) {
        return converter.prePopLangToDto(repo.save(converter.dtoToPrePopLang(dto)));
    }

    @Override
    public ProgrammingLanguagesDto update(ProgrammingLanguagesDto dto) {
        if (dto.getLanguageId() == null) {
            throw new IllegalArgumentException("Invalid id for PrePopLanguages: update");
        }

        ProgrammingLanguages foundLanguage = converter.dtoToPrePopLang(findById((dto.getLanguageId())));
        ProgrammingLanguages updatedLanguage = converter.dtoToPrePopLang(dto);

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
