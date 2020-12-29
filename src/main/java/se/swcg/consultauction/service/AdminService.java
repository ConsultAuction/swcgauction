package se.swcg.consultauction.service;

import se.swcg.consultauction.dto.AdminDto;
import se.swcg.consultauction.dto.AdminForm;

import java.time.LocalDate;
import java.util.List;

public interface AdminService {

    AdminDto findById(String adminId);
    List<AdminDto> findAll();
    AdminDto findByEmail(String email);
    List<AdminDto> findByRole(String role);
    List<AdminDto> findByActive(boolean active);
    List<AdminDto> findByLastActive(LocalDate lastActive);


    AdminDto create(AdminDto dto);
    AdminDto update(AdminForm adminForm);
    void  delete (AdminDto adminDto);


}
