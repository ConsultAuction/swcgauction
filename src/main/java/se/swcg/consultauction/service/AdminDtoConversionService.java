package se.swcg.consultauction.service;

import se.swcg.consultauction.dto.AdminDto;
import se.swcg.consultauction.entity.Admin;

import java.util.List;

public interface AdminDtoConversionService {

    Admin dtoToAdmin(AdminDto dto);
    AdminDto adminToDto(Admin admin);
    List<AdminDto> adminToDto(List<Admin> admins);
}
