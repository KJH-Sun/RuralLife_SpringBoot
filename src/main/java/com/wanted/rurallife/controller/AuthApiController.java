package com.wanted.rurallife.controller;


import com.wanted.rurallife.controller.dto.AuthDto.*;
import com.wanted.rurallife.service.AuthService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthApiController {

    private final AuthService authService;

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("hello");
    }

    @GetMapping("/authenticate")
    public ResponseEntity<TokenDto> login(
        String tel) {
        TokenDto tokenDto = authService.authorize(tel);
        return ResponseEntity.ok(tokenDto);
    }
}
