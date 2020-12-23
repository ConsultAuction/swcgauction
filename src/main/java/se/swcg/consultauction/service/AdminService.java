package se.swcg.consultauction.service;

import se.swcg.consultauction.dto.AdminDto;
import se.swcg.consultauction.dto.AdminForm;

import java.time.LocalDate;
import java.util.List;

public interface AdminService {

    AdminDto findById(String id);
    List<AdminDto> findAll();
    AdminDto findByEmail(String email);
    List<AdminDto> findByLastActive(LocalDate lastActive);
    List<AdminDto> findByRole(String role);

    AdminDto create(AdminForm adminForm);
    AdminDto update(AdminForm adminForm);
    void  delete (AdminDto adminDto);


}
