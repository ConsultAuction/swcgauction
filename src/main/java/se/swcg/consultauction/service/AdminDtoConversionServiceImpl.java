package se.swcg.consultauction.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.swcg.consultauction.dto.AdminDto;
import se.swcg.consultauction.entity.Admin;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class AdminDtoConversionServiceImpl implements AdminDtoConversionService{

    @Override
    public Admin dtoToAdmin(AdminDto dto) {
        return new Admin(dto.getAdminId(), dto.getFirstName(), dto.getLastName(), dto.getEmail(),
                dto.getPassword(), dto.getRole(), dto.isActive(), dto.getLastActive());
    }

    @Override
    public AdminDto adminToDto(Admin admin) {
        return new AdminDto(admin.getAdminId(), admin.getFirstName(), admin.getLastName(), admin.getEmail(),
                admin.getPassword(), admin.getRole(), admin.isActive(), admin.getLastActive());
    }

    @Override
    public List<AdminDto> adminToDto(List<Admin> admins) {

        if (admins == null) {
            admins = new ArrayList<>();
        }

        List<AdminDto> adminDtos = new ArrayList<>();

        for (Admin a: admins) {
            adminDtos.add(adminToDto(a));
        }

        return adminDtos;
    }
}
