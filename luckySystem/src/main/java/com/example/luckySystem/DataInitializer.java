package com.example.luckySystem;

import com.example.luckySystem.entity.EnumRole;
import com.example.luckySystem.entity.Role;
import com.example.luckySystem.repository.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final RoleRepo roleRepository;

    @Autowired
    public DataInitializer(RoleRepo roleRepository) {
        this.roleRepository = roleRepository;
    }
    @Override
    public void run(String... args) throws Exception {
        // Check if roles exist, if not, insert them
        if (roleRepository.findByName(EnumRole.HR_Manager).isEmpty()) {
            roleRepository.save(new Role(EnumRole.HR_Manager));
        }
        if (roleRepository.findByName(EnumRole.Accountant).isEmpty()) {
            roleRepository.save(new Role(EnumRole.Accountant));
        }
        if (roleRepository.findByName(EnumRole.Production_Manager).isEmpty()) {
            roleRepository.save(new Role(EnumRole.Production_Manager));
        }
        if (roleRepository.findByName(EnumRole.Store_Keeper).isEmpty()) {
            roleRepository.save(new Role(EnumRole.Store_Keeper));
        }
    }
}
