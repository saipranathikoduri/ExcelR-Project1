
package com.artforyou.store.controller;

import com.artforyou.store.entity.AdminEntity;
import com.artforyou.store.service.AdminService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(
    origins = "http://localhost:5175",
    allowCredentials = "true"
)
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestBody Map<String, String> request) {
        try {
            adminService.register(
                request.get("username"),
                request.get("password")
            );

            return ResponseEntity.ok(
                Map.of("message", "Admin account created successfully")
            );
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(
                Map.of("message", e.getMessage())
            );
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody Map<String, String> request,
            HttpSession session) {

        boolean valid = adminService.login(
            request.get("username"),
            request.get("password")
        );

        if (valid) {
            session.setAttribute("adminLoggedIn", true);

            return ResponseEntity.ok(
                Map.of("message", "Login successful")
            );
        }

        return ResponseEntity.status(401).body(
            Map.of("message", "Invalid username or password")
        );
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        session.invalidate();

        return ResponseEntity.ok(
            Map.of("message", "Logged out successfully")
        );
    }
}
