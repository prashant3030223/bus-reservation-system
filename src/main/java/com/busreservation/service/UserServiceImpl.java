package com.busreservation.service;

import com.busreservation.dto.UserRegistrationDto;
import com.busreservation.model.User;
import com.busreservation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void save(UserRegistrationDto registrationDto) {
        User user = new User();
        user.setFullName(registrationDto.getFullName());
        user.setEmail(registrationDto.getEmail());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        user.setRole("ROLE_USER");
        userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    /**
     * Is method ko naye fields save karne ke liye update kiya gaya hai.
     */
    @Override
    public void updateProfile(String email, String fullName, String mobile, String address) {
        User user = findByEmail(email);
        if (user != null) {
            user.setFullName(fullName);
            user.setMobile(mobile);     // Mobile number save karein
            user.setAddress(address);   // Address save karein
            userRepository.save(user);
        }
    }

    @Override // <-- Naya method
    public void updateProfilePhoto(String email, String photoUrl) {
        User user = findByEmail(email);
        if (user != null) {
            user.setProfilePhotoUrl(photoUrl);
            userRepository.save(user);
        }
    }
}