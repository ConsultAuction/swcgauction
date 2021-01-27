package se.swcg.consultauction.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import se.swcg.consultauction.dto.AdminDto;

import java.time.LocalDate;
import java.util.List;

public interface AdminService extends UserDetailsService {

    AdminDto findById(String adminId);
    List<AdminDto> findAll();
    AdminDto findByEmail(String email);
    List<AdminDto> findByRole(String role);
    List<AdminDto> findByActive(boolean active);
    List<AdminDto> findByLastActive(LocalDate lastActive);


    AdminDto create(AdminDto dto);
    AdminDto update(AdminDto dto);
    boolean  delete (String adminId);


}
