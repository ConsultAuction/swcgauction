package se.swcg.consultauction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.swcg.consultauction.dto.AdminDto;
import se.swcg.consultauction.dto.AdminForm;
import se.swcg.consultauction.repository.AdminRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService{

    AdminRepository adminRepo;
    AdminDtoConversionService converter;

    @Autowired
    public AdminServiceImpl(AdminRepository adminRepo, AdminDtoConversionService converter) {
        this.adminRepo = adminRepo;
        this.converter = converter;
    }

    @Override
    public AdminDto findById(String id) {
        return null;
    }

    @Override
    public List<AdminDto> findAll() {
        return null;
    }

    @Override
    public AdminDto findByEmail(String email) {
        return null;
    }

    @Override
    public List<AdminDto> findByLastActive(LocalDate lastActive) {
        return null;
    }

    @Override
    public List<AdminDto> findByRole(String role) {
        return null;
    }

    @Override
    public AdminDto create(AdminForm adminForm) {
        return null;
    }

    @Override
    public AdminDto update(AdminForm adminForm) {
        return null;
    }

    @Override
    public void delete(AdminDto adminDto) {

    }
}
