package com.example.car_rent.controller;

import com.example.car_rent.config.UserPrincipal;
import com.example.car_rent.entity.User;
import com.example.car_rent.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ProfileController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public String profile(
            Model model,
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {
        model.addAttribute("user", userPrincipal.getUser());
        return "auth/profile";
    }

    @PostMapping("/profile/change-password")
    public String changePassword(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @RequestParam String currentPassword,
            @RequestParam String newPassword,
            @RequestParam String confirmPassword,
            RedirectAttributes redirectAttributes
    ) {

        if (!newPassword.equals(confirmPassword)) {
            redirectAttributes.addFlashAttribute("error", "Passwords do not match");
            return "redirect:/profile";
        }

        boolean success = userService.changePassword(
                userPrincipal.getUser(),
                currentPassword,
                newPassword
        );

        if (!success) {
            redirectAttributes.addFlashAttribute("error", "Current password is incorrect");
            return "redirect:/profile";
        }

        redirectAttributes.addFlashAttribute("success", "Password updated successfully");
        return "redirect:/profile";
    }

    @PostMapping("/profile/update")
    public String updateProfile(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @RequestParam String name,
            @RequestParam String phone,
            RedirectAttributes redirectAttributes
    ) {
        User user = userPrincipal.getUser();

        user.setName(name);
        user.setPhone(phone);

        userService.save(user);

        redirectAttributes.addFlashAttribute(
                "success", "Profile updated successfully"
        );
        return "redirect:/profile";
    }

}
