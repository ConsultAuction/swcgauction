package se.swcg.consultauction.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.swcg.consultauction.dto.*;
import se.swcg.consultauction.entity.*;

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
                dto.getRole(), dto.getDateOfSignUp(), dto.getLastActive(), dto.isActive(), dto.getImage(), dto.getContact(), dto.getConsultantDetails());
    }

    @Override
    public UserDto userToDto(User user) {
        return new UserDto(user.getUserId(), user.getCompanyName(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(),
                user.getRole(), user.getDateOfSignUp(), user.getLastActive(), user.isActive(), user.getImage(), user.getContact(), user.getConsultantDetails());
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

    @Override
    public Skills dtoToSkills(SkillsDto dto) {
        return new Skills(dto.getSkillsId(), dto.getSkillName());
    }

    @Override
    public SkillsDto SkillsToDto(Skills skills) {
        return new SkillsDto(skills.getSkillsId(),skills.getSkillName());
    }

    @Override
    public List<SkillsDto> SkillsToDto(List<Skills> skills) {
        if (skills == null) {
            skills = new ArrayList<>();
        }

        List<SkillsDto> skillsDtos = new ArrayList<>();

        for (Skills s: skills) {
            skillsDtos.add(SkillsToDto(s));
        }

        return skillsDtos;
    }


    @Override
    public ProjectOffer dtoToProjectOffer(ProjectOfferDto dto) {
        return new ProjectOffer(dto.getProjectOfferId(),dto.getUser(),dto.getProject(),dto.isAccepted(),
                dto.isRejected(),dto.getStartTime(),dto.isSelected(),dto.getBids());
    }

    @Override
    public ProjectOfferDto projectOfferToDto(ProjectOffer projectOffer) {
        return new ProjectOfferDto(projectOffer.getProjectOfferId(),projectOffer.getUser(),projectOffer.getProject(),
                projectOffer.isAccepted(),projectOffer.isRejected(),projectOffer.getStartTime(),
                projectOffer.isSelected(),projectOffer.getBids());
    }

    @Override
    public List<ProjectOfferDto> projectOfferToDto(List<ProjectOffer> projectOffers) {
        if (projectOffers == null) {
            projectOffers = new ArrayList<>();
        }

        List<ProjectOfferDto> projectOfferDtos = new ArrayList<>();

        for (ProjectOffer p: projectOffers) {
            projectOfferDtos.add(projectOfferToDto(p));
        }

        return projectOfferDtos;
    }

    @Override
    public ProjectOfferForm DtoToProjectOfferForm(ProjectOfferDto dto) {
        return new ProjectOfferForm(dto.getProjectOfferId(),dto.getUser(),dto.getProject(),dto.isAccepted(),
                dto.isRejected(),dto.getStartTime(),dto.isSelected(),dto.getBids());
    }

    @Override
    public ProjectOffer ProjectOfferFormToProjectOffer(ProjectOfferForm dto) {
        return new ProjectOffer(dto.getProjectOfferId(),dto.getUser(),dto.getProject(),dto.isAccepted(),
                dto.isRejected(),dto.getStartTime(),dto.isSelected(),dto.getBids());
    }

    @Override
    public Project dtoToProject(ProjectDto dto) {
        return new Project(dto.getProjectId(),dto.getProjectName(),dto.getStartDate(),dto.getEndDate(),
                dto.getWorkLoad(),dto.getDescription(),dto.getLocated(), dto.isDistanceWork(), dto.isCompanyHardware(),
                dto.getContactName(),dto.getContactEmail(),dto.getContactPhoneNumber(),dto.getUser());
    }

    @Override
    public ProjectDto projectToDto(Project project) {
        return null;
    }

    @Override
    public List<ProjectDto> projectToDto(List<Project> project) {
        if (project == null) {
            project = new ArrayList<>();
        }

        List<ProjectDto> projectDtos = new ArrayList<>();

        for (Project p: project) {
            projectDtos.add(projectToDto(p));
        }

        return projectDtos;
    }

    @Override
    public ProjectForm DtoToProjectForm(ProjectDto dto) {
        return new ProjectForm(dto.getProjectId(),dto.getProjectName(),dto.getStartDate(),dto.getEndDate(),
                dto.getWorkLoad(),dto.getDescription(),dto.getLocated(),dto.isDistanceWork(),dto.isCompanyHardware(),
                dto.getContactName(),dto.getContactEmail(),dto.getContactPhoneNumber(), dto.getUser());
    }

    @Override
    public Project ProjectFormToProject(ProjectForm dto) {
        return new Project(dto.getProjectName(),dto.getStartDate(),dto.getEndDate(),
                dto.getWorkLoad(),dto.getDescription(),dto.getLocated(),dto.isDistanceWork(),dto.isCompanyHardware(),
                dto.getContactName(),dto.getContactEmail(),dto.getContactEmail(),dto.getUser());
    }
}
