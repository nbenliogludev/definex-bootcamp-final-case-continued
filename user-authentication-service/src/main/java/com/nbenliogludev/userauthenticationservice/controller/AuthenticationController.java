package com.nbenliogludev.userauthenticationservice.controller;

import com.nbenliogludev.userauthenticationservice.dto.request.AuthenticationRequestDTO;
import com.nbenliogludev.userauthenticationservice.dto.request.UserCreateRequestDTO;
import com.nbenliogludev.userauthenticationservice.dto.response.AuthenticationResponseDTO;
import com.nbenliogludev.userauthenticationservice.dto.response.RestResponse;
import com.nbenliogludev.userauthenticationservice.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;
    @PostMapping("/v1/register")
    public ResponseEntity<RestResponse<AuthenticationResponseDTO>> register(
            @RequestBody UserCreateRequestDTO request
    ) {
        System.out.println(request.toString());
        return ResponseEntity.ok(RestResponse.of(service.register(request)));
    }

    @PostMapping("/v1/authenticate")
    public ResponseEntity<RestResponse<AuthenticationResponseDTO>> authenticate(
            @RequestBody AuthenticationRequestDTO request
    ) {
        return ResponseEntity.ok(RestResponse.of(service.authenticate(request)));
    }

}
