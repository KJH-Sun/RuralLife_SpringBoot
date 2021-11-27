package com.wanted.rurallife.controller;


import static com.wanted.rurallife.common.util.constants.ResponseConstants.CREATED;
import static com.wanted.rurallife.common.util.constants.ResponseConstants.OK;

import com.wanted.rurallife.controller.dto.VolunteerDto.SignUpDto;
import com.wanted.rurallife.service.VolunteerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/volunteer")
public class VolunteerApiController {

    private final VolunteerService volunteerService;

    @PostMapping()
    public ResponseEntity<Void> signUp(@RequestBody SignUpDto signUpDto) {
        volunteerService.signUp(signUpDto);
        return CREATED;
    }

}
