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

    List<ProjectOfferDto> findByClientId(String  clientId);

    ProjectOfferDto setAccepted(String offerId);
    ProjectOfferDto setRejected(String offerId);
    ProjectOfferDto setSelected(String offerId);

    ProjectOfferDto createProjectOffer(CreateProjectOfferRequest projectOfferRequest);
    ProjectOfferDto updateProjectOffer(String ProjectOfferId, CreateProjectOfferRequest projectOfferRequest);

    void deleteAllSelectedWithFalse();
}
