package se.swcg.consultauction.service;

import se.swcg.consultauction.exception.ResourceNotFoundException;

import java.util.List;

public class ServiceHelper {

    //Takes any kind of List and throws exception if empty
    protected <T> List<T> checkIfListIsEmpty(List<T> dto, String message) {
        if (dto.isEmpty()) throw new ResourceNotFoundException(message);
        return dto;
    }
}
