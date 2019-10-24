package com.sport.betting.Controller;

import com.sport.betting.Repo.BetRepo;
import com.sport.betting.Service.BetService;
import com.sport.betting.domain.User;
import com.sport.betting.domain.dto.BetDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/bet")
public class BetController {

    private BetService betService;

    @Autowired
    public BetController(BetService betService) {
        this.betService = betService;
    }


    @GetMapping
    public HashMap<String, Object> bet(@RequestParam(value = "perPage", defaultValue = "10") int perPage,
                               @RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
                               @AuthenticationPrincipal User user) {
        PageRequest pageR = new PageRequest(currentPage - 1,perPage);
        Page<BetDto> betPage = betService.betList(pageR, user);
        HashMap<String, Object> response = new HashMap<>();
        response.put("content", betPage.getContent());
        response.put("totalPages", betPage.getTotalPages());
        return response;
    }
}
