package com.example.hospital.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @GetMapping("/apply-for-vaccine/{area}")
    public String applyForVaccine() {

        return "Vaccine applied successfully";
    }
}
