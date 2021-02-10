package se.swcg.consultauction.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.swcg.consultauction.dto.AdminDto;
import se.swcg.consultauction.dto.ProjectOfferDto;
import se.swcg.consultauction.dto.ProjectOfferForm;
import se.swcg.consultauction.entity.Admin;
import se.swcg.consultauction.entity.ProjectOffer;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class ProjectOfferDtoConversionImpl implements ProjectOfferDtoConversion {


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
}


