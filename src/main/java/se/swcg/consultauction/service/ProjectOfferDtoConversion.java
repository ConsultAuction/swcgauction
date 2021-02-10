package se.swcg.consultauction.service;

import se.swcg.consultauction.dto.ClientDto;
import se.swcg.consultauction.dto.ClientForm;
import se.swcg.consultauction.dto.ProjectOfferDto;
import se.swcg.consultauction.dto.ProjectOfferForm;
import se.swcg.consultauction.entity.Client;
import se.swcg.consultauction.entity.ProjectOffer;

import java.util.List;

public interface ProjectOfferDtoConversion {

    ProjectOffer dtoToProjectOffer(ProjectOfferDto dto);

    ProjectOfferDto projectOfferToDto(ProjectOffer projectOffer);
    List<ProjectOfferDto> projectOfferToDto(List<ProjectOffer> projectOffers);

    ProjectOfferForm DtoToProjectOfferForm(ProjectOfferDto dto);

    ProjectOffer ProjectOfferFormToProjectOffer(ProjectOfferForm dto);

}
