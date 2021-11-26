package com.wanted.rurallife.controller;


import com.wanted.rurallife.controller.dto.AuthDto.TokenDto;
import com.wanted.rurallife.controller.dto.TownDto.RecommendTownListDto;
import com.wanted.rurallife.controller.dto.TownDto.TownInfoDto;
import com.wanted.rurallife.service.TownService;
import javax.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/town")
@RequiredArgsConstructor
public class TownApiController {

    private final TownService townService;

    @GetMapping("/list")
    public ResponseEntity<RecommendTownListDto> recommendTownList(
        @AuthenticationPrincipal String tel) {
        RecommendTownListDto recommendTownListDto = townService.recommendTown(tel);
        return ResponseEntity.ok(recommendTownListDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TownInfoDto> getTownInfo(@PathVariable @NotEmpty Long id) {
        TownInfoDto townInfoDto = townService.getTownInfo(id);
        return ResponseEntity.ok(townInfoDto);
    }

}
