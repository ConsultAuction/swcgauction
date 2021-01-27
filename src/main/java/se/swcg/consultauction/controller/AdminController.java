package se.swcg.consultauction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.swcg.consultauction.dto.AdminDto;
import se.swcg.consultauction.entity.Admin;
import se.swcg.consultauction.security.SecurityAdmin;
import se.swcg.consultauction.service.AdminService;
import se.swcg.consultauction.service.AdminServiceImpl;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "/api/admin")
public class AdminController {

    @Autowired
    private AdminServiceImpl adminService;

    @GetMapping
    public ResponseEntity<List<AdminDto>> findAll() {
        return ResponseEntity.ok(adminService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdminDto> findById(@PathVariable String id) {
        return ResponseEntity.ok(adminService.findById(id));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<AdminDto> findByEmail(@PathVariable String email) {
        return ResponseEntity.ok(adminService.findByEmail(email));
    }

    @GetMapping("/role/{role}")
    public ResponseEntity<List<AdminDto>> findByRole(@PathVariable String role) {
        return ResponseEntity.ok(adminService.findByRole(role));
    }

    @GetMapping("/active/{active}")
    public ResponseEntity<List<AdminDto>> findByActive(@PathVariable boolean active) {
        return ResponseEntity.ok(adminService.findByActive(active));
    }

    @GetMapping("/lastActive/{lastActive}")
    public ResponseEntity<List<AdminDto>> findByLastActive(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate lastActive) {
        return ResponseEntity.ok(adminService.findByLastActive(lastActive));
    }

    @PostMapping
    public ResponseEntity<AdminDto> create(@Valid @RequestBody AdminDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(adminService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdminDto> update(@PathVariable String id, @RequestBody AdminDto updated) {
        if (!updated.getAdminId().equals(id)) throw new IllegalArgumentException("Id does not match.");

        return ResponseEntity.ok(adminService.update(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        boolean isRemoved = adminService.delete(id);
        if (!isRemoved) throw new IllegalArgumentException("Something went wrong trying to delete admin with id: " + id);

        return new ResponseEntity<>("Admin with id: " + id + " was successfully removed.", HttpStatus.OK);
    }

}
