package com.example.Best_Prac.Config;

import com.example.Best_Prac.Entity.RoleModel;
import com.example.Best_Prac.Entity.UserModel;
import com.example.Best_Prac.Repo.RoleRepository;
import com.example.Best_Prac.Repo.UserRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Component
public class DataLoader implements ApplicationRunner {

    private final BCryptPasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public DataLoader(BCryptPasswordEncoder passwordEncoder, RoleRepository roleRepository, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws InterruptedException {
        // Save roles first
        RoleModel bidderRole = new RoleModel("BIDDER");
        RoleModel approverRole = new RoleModel("APPROVER");

        bidderRole = roleRepository.save(bidderRole);
        approverRole = roleRepository.save(approverRole);

        // Now save users with the persisted roles
        userRepository.save(new UserModel("bidder1", "bidderemail@gmail.com", "companyOne", passwordEncoder.encode("bidder123"), bidderRole));
        userRepository.save(new UserModel("bidder2", "bidderemail2@gmail.com", "companyTwo", passwordEncoder.encode("bidder789"), bidderRole));

        // Ensure the password for the approver is also encoded
        userRepository.save(new UserModel("approver", "approveremail@gmail.com", "defaultCompany", passwordEncoder.encode("approver123"), approverRole));
    }
}
