package com.busreservation.service;

import com.busreservation.dto.UserRegistrationDto;
import com.busreservation.model.User;

public interface UserService {
    
    void save(UserRegistrationDto registrationDto);
    
    User findByEmail(String email);
    
    // Is method ko naye parameters ke saath update kiya gaya hai
    void updateProfile(String email, String fullName, String mobile, String address); 
    void updateProfilePhoto(String email, String photoUrl); // <-- Yeh naya method
}