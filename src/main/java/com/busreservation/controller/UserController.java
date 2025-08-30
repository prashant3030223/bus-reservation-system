package com.busreservation.controller;

import com.busreservation.model.User;
import com.busreservation.service.FileStorageService; // Nayi import
import com.busreservation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile; // Nayi import
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired // Naya Autowire
    private FileStorageService fileStorageService;

    @GetMapping("/profile")
    public String viewProfile(Model model, Authentication authentication) {
        if (authentication == null) {
            return "redirect:/login";
        }
        User currentUser = userService.findByEmail(authentication.getName());
        model.addAttribute("user", currentUser);
        return "profile";
    }

    @PostMapping("/profile/update")
    public String updateProfile(@RequestParam String fullName,
                                @RequestParam String mobile,
                                @RequestParam String address,
                                @RequestParam(value = "profileImage", required = false) MultipartFile profileImage, // Naya parameter
                                Authentication authentication,
                                RedirectAttributes redirectAttributes) {
        if (authentication == null) {
            return "redirect:/login";
        }
        String email = authentication.getName();

        // General profile information update
        userService.updateProfile(email, fullName, mobile, address);

        // Profile image upload
        if (profileImage != null && !profileImage.isEmpty()) {
            try {
                String photoUrl = fileStorageService.storeFile(profileImage);
                userService.updateProfilePhoto(email, photoUrl);
                redirectAttributes.addFlashAttribute("successMessage", "Profile and photo updated successfully!");
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("errorMessage", "Could not upload profile image: " + e.getMessage());
            }
        } else {
             redirectAttributes.addFlashAttribute("successMessage", "Your profile has been updated successfully!");
        }
       
        return "redirect:/profile";
    }
}