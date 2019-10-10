package com.sport.betting.Controller;

import com.sport.betting.Repo.BetRepo;
import com.sport.betting.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

@Controller
@RequestMapping("/")
public class MainController {
    private final BetRepo betRepo;

    @Autowired
    public MainController(BetRepo betRepo) {
        this.betRepo = betRepo;
    }

    @GetMapping
    public String main(Model model, @AuthenticationPrincipal User user) {
        HashMap<Object, Object> data = new HashMap<>();
        data.put("profile", user);
        model.addAttribute("frontendData", data);
        return "index";
    }
}
