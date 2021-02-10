package se.swcg.consultauction.service;

import se.swcg.consultauction.dto.ProjectDto;
import se.swcg.consultauction.dto.ProjectOfferDto;
import se.swcg.consultauction.dto.UserDto;
import se.swcg.consultauction.entity.ProjectOffer;
import se.swcg.consultauction.entity.User;

import java.util.List;

public interface ProjectOfferService {

    List<ProjectOfferDto> findAll();

    ProjectOfferDto findById(String projectOfferId);

    List<ProjectOfferDto> findByAcceptedByUserId(UserDto  user);

    List<ProjectOfferDto> findByRejectByUserId(UserDto user);

    List<ProjectOfferDto> findBySelectedByUserId(UserDto user);







}