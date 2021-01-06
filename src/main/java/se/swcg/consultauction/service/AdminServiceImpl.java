package se.swcg.consultauction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.swcg.consultauction.dto.AdminDto;
import se.swcg.consultauction.entity.Admin;
import se.swcg.consultauction.exception.ResourceNotFoundException;
import se.swcg.consultauction.repository.AdminRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    AdminRepository repo;
    @Autowired
    AdminDtoConversionService converter;

    @Override
    public AdminDto findById(String adminId) {
        return converter.adminToDto(
                repo.findById(adminId)
                        .orElseThrow(() -> new ResourceNotFoundException("Could not find admin with id: " + adminId)));
    }

    @Override
    public List<AdminDto> findAll() {
        return checkIfEmpty(converter.adminToDto(repo.findAll()), "Could not find any admins");
    }

    @Override
    public AdminDto findByEmail(String email) {
        return converter.adminToDto(
                repo.findByEmail(email)
                        .orElseThrow(() -> new ResourceNotFoundException("Could not find a admin with email: " + email)));
    }

    @Override
    public List<AdminDto> findByRole(String role) {
        return checkIfEmpty(converter.adminToDto(repo.findByRole(role)), "Could not find any admins by role: " + role);
    }

    @Override
    public List<AdminDto> findByActive(boolean active) {
        return checkIfEmpty(converter.adminToDto(repo.findByActive(active)), "Could not find any admins by active");
    }

    @Override
    public List<AdminDto> findByLastActive(LocalDate lastActive) {
        return checkIfEmpty(converter.adminToDto(repo.findByLastActive(lastActive)), "Could not find any admins by last active: " + lastActive);
    }

    @Override
    public AdminDto create(AdminDto dto) {
        checkIfValid(dto);

        return converter.adminToDto(repo.save(converter.dtoToAdmin(dto)));
    }

    @Override
    public AdminDto update(AdminDto dto) {
        if (dto.getAdminId() == null) {
            throw new IllegalArgumentException("Invalid id for admin: update");
        }

        Admin foundAdmin = converter.dtoToAdmin(findById(dto.getAdminId()));
        Admin updatedAdmin = converter.dtoToAdmin(dto);

        //Check to not throw exception if email is the same
        if (!foundAdmin.getEmail().equals(dto.getEmail())) checkIfValid(dto);

        foundAdmin.setFirstName(updatedAdmin.getFirstName());
        foundAdmin.setLastName(updatedAdmin.getLastName());
        foundAdmin.setEmail(updatedAdmin.getEmail());
        foundAdmin.setPassword(updatedAdmin.getPassword());
        foundAdmin.setRole(updatedAdmin.getRole());
        foundAdmin.setActive(updatedAdmin.isActive());
        foundAdmin.setLastActive(updatedAdmin.getLastActive());

        return converter.adminToDto(repo.save(foundAdmin));
    }

    @Override
    public boolean delete(String adminId) {
        repo.delete(converter.dtoToAdmin(findById(adminId)));

        //Returns true if admin is not present
        return !repo.findById(adminId).isPresent();
    }

    private List<AdminDto> checkIfEmpty(List<AdminDto> adminDtos, String message) {
        if(adminDtos.isEmpty()){
            throw new ResourceNotFoundException(message);
        }

        return adminDtos;
    }

    //Checks with the DB if value already exists
    private AdminDto checkIfValid(AdminDto dto) {
        //Maybe add trim and all lowercase
        if (repo.findByEmail(dto.getEmail()).isPresent()) throw new IllegalArgumentException("Email already exists :" + dto.getEmail());

        return dto;
    }
}
