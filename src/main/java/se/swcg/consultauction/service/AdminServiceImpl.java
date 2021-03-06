/*
package se.swcg.consultauction.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import se.swcg.consultauction.dto.AdminDto;
import se.swcg.consultauction.entity.Admin;
import se.swcg.consultauction.exception.ResourceNotFoundException;
import se.swcg.consultauction.security.SecurityUser;

import java.time.LocalDate;
import java.util.List;

import static se.swcg.consultauction.service.ServiceHelper.checkIfListIsEmpty;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminRepository repo;
    @Autowired
    AdminDtoConversionService converter;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    */
/*//*
/Constructor not working with test right now.
    @Autowired
    public AdminServiceImpl(AdminRepository repo, AdminDtoConversionService converter, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.repo = repo;
        this.converter = converter;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }*//*


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return new SecurityUser(
                repo.findByEmail(email)
                        .orElseThrow(() -> new UsernameNotFoundException("Could not find admin with email: " + email))
        );
        */
/*
        Optional<Admin> admin = repo.findByEmail(email);
        if (!admin.isPresent()) throw new UsernameNotFoundException("Could not find admin with email: " + email);
        return new User(admin.get().getEmail(), admin.get().getPassword(), new ArrayList<>());*//*

    }

    @Override
    public AdminDto findById(String adminId) {
        return converter.adminToDto(
                repo.findById(adminId)
                        .orElseThrow(() -> new ResourceNotFoundException("Could not find admin with id: " + adminId)));
    }

    @Override
    public List<AdminDto> findAll() {
        return checkIfListIsEmpty(converter.adminToDto(repo.findAll()), "Could not find any admins");
    }

    @Override
    public AdminDto findByEmail(String email) {
        return converter.adminToDto(
                repo.findByEmail(email)
                        .orElseThrow(() -> new ResourceNotFoundException("Could not find a admin with email: " + email)));

    }

    @Override
    public List<AdminDto> findByRole(String role) {
        return checkIfListIsEmpty(converter.adminToDto(repo.findByRole(role)), "Could not find any admins by role: " + role);
    }

    @Override
    public List<AdminDto> findByActive(boolean active) {
        return checkIfListIsEmpty(converter.adminToDto(repo.findByActive(active)), "Could not find any admins by active");
    }

    @Override
    public List<AdminDto> findByLastActive(LocalDate lastActive) {
        return checkIfListIsEmpty(converter.adminToDto(repo.findByLastActive(lastActive)), "Could not find any admins by last active: " + lastActive);
    }

    @Override
    public AdminDto create(AdminDto dto) {
        checkIfEmailIsValid(dto);

        Admin newAdmin = new Admin();

        BeanUtils.copyProperties(dto, newAdmin);
        newAdmin.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));


        return converter.adminToDto(repo.save(newAdmin));
        //        return converter.adminToDto(repo.save(converter.dtoToAdmin(dto)));
    }

    @Override
    public AdminDto update(AdminDto dto) {
        if (dto.getAdminId() == null) {
            throw new IllegalArgumentException("Invalid id for admin: update");
        }

        Admin foundAdmin = converter.dtoToAdmin(findById(dto.getAdminId()));
        Admin updatedAdmin = converter.dtoToAdmin(dto);

        //Check to not throw exception if email is the same
        if (!foundAdmin.getEmail().equals(dto.getEmail())) checkIfEmailIsValid(dto);

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

    //Checks with the DB if value already exists
    private AdminDto checkIfEmailIsValid(AdminDto dto) {
        //Maybe add trim and all lowercase
        if (repo.findByEmail(dto.getEmail()).isPresent()) throw new IllegalArgumentException("Email already exists :" + dto.getEmail());

        return dto;
    }
}
*/
