package kea.lhk.rito_api.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class Dashboard {
    @GetMapping("/dashboard")
    public String dashBoard() {
        return "dashboard";
    }
}
