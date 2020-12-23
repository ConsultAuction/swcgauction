package se.swcg.consultauction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.swcg.consultauction.dto.AdminDto;
import se.swcg.consultauction.dto.AdminForm;
import se.swcg.consultauction.entity.Admin;
import se.swcg.consultauction.exception.ResourceNotFoundException;
import se.swcg.consultauction.repository.AdminRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService{

    AdminRepository repo;
    AdminDtoConversionService converter;

    @Autowired
    public AdminServiceImpl(AdminRepository repo, AdminDtoConversionService converter) {
        this.repo = repo;
        this.converter = converter;
    }

    @Override
    public AdminDto findById(String adminId) {
        return converter.adminToDto(
                repo.findById(adminId)
                        .orElseThrow(() -> new ResourceNotFoundException("Could not find a admin with id: " + adminId)));
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
        return checkIfEmpty(converter.adminToDto(repo.findByRole(role)), "Could not find any admins by role");
    }

    @Override
    public List<AdminDto> findByActive(boolean active) {
        return checkIfEmpty(converter.adminToDto(repo.findByActive(active)), "Could not find any admins by active");
    }

    @Override
    public List<AdminDto> findByLastActive(LocalDate lastActive) {
        return checkIfEmpty(converter.adminToDto(repo.findByLastActive(lastActive)), "Could not find any admins by last active");
    }

    @Override
    public AdminDto create(AdminForm adminForm) {
        Admin newAdmin = new Admin(adminForm.getFirstName(), adminForm.getLastName(), adminForm.getEmail(),
                adminForm.getPassword(), adminForm.getRole(), adminForm.isActive(), adminForm.getLastActive());

        return converter.adminToDto(repo.save(newAdmin));

        //return converter.adminToDto(repo.save(converter.adminFormToAdmin(adminForm)));
    }

    @Override
    public AdminDto update(AdminForm adminForm) {
        if (adminForm.getAdminId() == null) {
            throw new IllegalArgumentException("Invalid id for admin");
        }

        Admin foundAdmin = converter.dtoToAdmin(findById(adminForm.getAdminId()));
        Admin updatedAdmin = converter.adminFormToAdmin(adminForm);

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
    public void delete(AdminDto adminDto) {
        Admin adminToDelete = converter.dtoToAdmin(findById(adminDto.getAdminId()));

        repo.delete(adminToDelete);

    }

    private List<AdminDto> checkIfEmpty(List<AdminDto> adminDtos, String message) {
        if(adminDtos.isEmpty()){
            throw new ResourceNotFoundException(message);
        }

        return adminDtos;
    }
}
