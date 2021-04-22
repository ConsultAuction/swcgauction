package se.swcg.consultauction.service;

import se.swcg.consultauction.dto.ProjectDto;
import se.swcg.consultauction.dto.ProjectOfferDto;
import se.swcg.consultauction.dto.UserDto;
import se.swcg.consultauction.entity.ProjectOffer;
import se.swcg.consultauction.entity.User;
import se.swcg.consultauction.model.CreateProjectOfferRequest;

import java.util.List;

public interface ProjectOfferService {

    List<ProjectOfferDto> findAll();
    ProjectOfferDto findById(String projectOfferId);

    List<ProjectOfferDto> findByConsultantId(String consultantId);

    List<ProjectOfferDto> findByAcceptedByUserId(UserDto  user);
    List<ProjectOfferDto> findByRejectByUserId(UserDto user);
    List<ProjectOfferDto> findBySelectedByUserId(UserDto user);

    ProjectOfferDto createProjectOffer(CreateProjectOfferRequest projectOfferRequest);
    ProjectOfferDto updateProjectOffer(String ProjectOfferId, CreateProjectOfferRequest projectOfferRequest);





}
