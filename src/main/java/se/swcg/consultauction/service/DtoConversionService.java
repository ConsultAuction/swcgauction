package se.swcg.consultauction.service;

import se.swcg.consultauction.dto.*;
import se.swcg.consultauction.entity.*;

import java.util.List;

public interface DtoConversionService {

    User dtoToUser(UserDto dto);
    UserDto userToDto(User user);
    List<UserDto> userToDto(List<User> users);

    ProgrammingLanguages dtoToPrePopLang(ProgrammingLanguagesDto dto);
    ProgrammingLanguagesDto prePopLangToDto(ProgrammingLanguages programmingLanguages);
    List<ProgrammingLanguagesDto> prePopLangToDto(List<ProgrammingLanguages> languages);


    Skills dtoToSkills(SkillsDto dto);
    SkillsDto SkillsToDto(Skills skills);
    List<SkillsDto> SkillsToDto(List<Skills> skills);

    ProjectOffer dtoToProjectOffer(ProjectOfferDto dto);
    ProjectOfferDto projectOfferToDto(ProjectOffer projectOffer);
    List<ProjectOfferDto> projectOfferToDto(List<ProjectOffer> projectOffers);
    ProjectOfferForm DtoToProjectOfferForm(ProjectOfferDto dto);
    ProjectOffer ProjectOfferFormToProjectOffer(ProjectOfferForm dto);

    Project dtoToProject(ProjectDto dto);
    ProjectDto projectToDto(Project project);
    List<ProjectDto> projectToDto(List<Project> project);
    ProjectForm DtoToProjectForm(ProjectDto dto);
    Project ProjectFormToProject(ProjectForm dto);

}
