package se.swcg.consultauction.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.swcg.consultauction.dto.ProjectOfferDto;
import se.swcg.consultauction.dto.UserDto;
import se.swcg.consultauction.exception.ResourceNotFoundException;
import se.swcg.consultauction.repository.ProjectOfferRepository;
import java.util.ArrayList;
import java.util.List;



@Service
@Transactional(readOnly = true)
public class ProjectOfferServiceImpl implements ProjectOfferService {

    @Autowired
     ProjectOfferRepository pRepository;

    @Autowired
    ProjectOfferDtoConversion pConverter;


    @Override
    public List<ProjectOfferDto> findAll() {
        return pConverter.projectOfferToDto(pRepository.findAll());
    }

    @Override
    public ProjectOfferDto findById(String projectOfferId) {
        return pConverter.projectOfferToDto(pRepository.findById(projectOfferId).orElseThrow(() ->
                new ResourceNotFoundException("Could not find ProjectOffer with Id: " +projectOfferId)));
    }

    @Override
    public List<ProjectOfferDto> findByAcceptedByUserId(UserDto user) {
        List<ProjectOfferDto> tmp = findAll();

        List<ProjectOfferDto> arg = new ArrayList<>();

        for (ProjectOfferDto p: tmp){
          if (user.getUserId().equals(p.getUser().getUserId()) && p.isAccepted() ){
              arg.add(p);
          }
        }

        return arg;
    }

    @Override
    public List<ProjectOfferDto> findByRejectByUserId(UserDto user) {
        List<ProjectOfferDto> tmp = findAll();

        List<ProjectOfferDto> arg = new ArrayList<>();

        for (ProjectOfferDto p: tmp){
            if (user.getUserId().equals(p.getUser().getUserId()) && p.isRejected() ){
                arg.add(p);
            }
        }

        return arg;
    }

    @Override
    public List<ProjectOfferDto> findBySelectedByUserId(UserDto user) {
        List<ProjectOfferDto> tmp = findAll();

        List<ProjectOfferDto> arg = new ArrayList<>();

        for (ProjectOfferDto p: tmp){
            if (user.getUserId().equals(p.getUser().getUserId()) && p.isSelected() ){
                arg.add(p);
            }
        }

        return arg;
    }
}
