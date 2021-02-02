package se.swcg.consultauction.service;

import se.swcg.consultauction.dto.ProgrammingLanguagesDto;
import se.swcg.consultauction.dto.UserDto;
import se.swcg.consultauction.entity.ProgrammingLanguages;
import se.swcg.consultauction.entity.User;

import java.util.List;

public interface DtoConversionService {

    User dtoToUser(UserDto dto);
    UserDto userToDto(User user);
    List<UserDto> userToDto(List<User> users);

    ProgrammingLanguages dtoToPrePopLang(ProgrammingLanguagesDto dto);
    ProgrammingLanguagesDto prePopLangToDto(ProgrammingLanguages programmingLanguages);
    List<ProgrammingLanguagesDto> prePopLangToDto(List<ProgrammingLanguages> languages);
}
