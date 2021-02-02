package se.swcg.consultauction.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.swcg.consultauction.dto.ProgrammingLanguagesDto;
import se.swcg.consultauction.dto.UserDto;
import se.swcg.consultauction.entity.ProgrammingLanguages;
import se.swcg.consultauction.entity.User;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class DtoConversionServiceImpl implements DtoConversionService {

    /*
    *  User
    * */
    @Override
    public User dtoToUser(UserDto dto) {
        return new User(dto.getUserId(), dto.getCompanyName(), dto.getFirstName(), dto.getLastName(), dto.getEmail(), dto.getPassword(),
                dto.getRole(), dto.getDateOfSignUp(), dto.getLastActive(), dto.isActive(), dto.isAvailableForHire(), dto.getImage(), dto.getContact());
    }

    @Override
    public UserDto userToDto(User user) {
        return new UserDto(user.getUserId(), user.getCompanyName(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(),
                user.getRole(), user.getDateOfSignUp(), user.getLastActive(), user.isActive(), user.isAvailableForHire(), user.getImage(), user.getContact());
    }

    @Override
    public List<UserDto> userToDto(List<User> users) {
        if (users == null){
            users = new ArrayList<>();
        }
        List<UserDto> userDtos = new ArrayList<>();

        for(User c: users){
            userDtos.add(userToDto(c));
        }
        return userDtos;
    }


    /*
    * Programming Languages
    * */
    @Override
    public ProgrammingLanguages dtoToPrePopLang(ProgrammingLanguagesDto dto) {
        return new ProgrammingLanguages(dto.getLanguageId(), dto.getLanguage());
    }

    @Override
    public ProgrammingLanguagesDto prePopLangToDto(ProgrammingLanguages programmingLanguages) {
        return new ProgrammingLanguagesDto(programmingLanguages.getLanguageId(), programmingLanguages.getLanguage());
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
