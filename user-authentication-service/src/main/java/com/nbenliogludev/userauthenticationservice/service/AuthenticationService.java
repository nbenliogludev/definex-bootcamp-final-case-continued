package com.nbenliogludev.userauthenticationservice.service;

import com.nbenliogludev.userauthenticationservice.dto.request.AuthenticationRequestDTO;
import com.nbenliogludev.userauthenticationservice.dto.request.UserCreateRequestDTO;
import com.nbenliogludev.userauthenticationservice.dto.response.AuthenticationResponseDTO;
import com.nbenliogludev.userauthenticationservice.entity.Role;
import com.nbenliogludev.userauthenticationservice.entity.Token;
import com.nbenliogludev.userauthenticationservice.entity.TokenType;
import com.nbenliogludev.userauthenticationservice.entity.User;
import com.nbenliogludev.userauthenticationservice.repository.TokenRepository;
import com.nbenliogludev.userauthenticationservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponseDTO register(UserCreateRequestDTO request) {
        User user = User.builder()
                .firstname(request.name())
                .lastname(request.surname())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .role(Role.TEAM_MEMBER)
                .build();

        User savedUser = repository.save(user);

        String jwtToken = jwtService.generateTokenWithRolesAndPermissions(savedUser);
        String refreshToken = jwtService.generateRefreshToken(user);
        saveUserToken(savedUser, jwtToken);
        return AuthenticationResponseDTO.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .id(savedUser.getId())
                .build();
    }

    public AuthenticationResponseDTO authenticate(AuthenticationRequestDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );

        User user = repository.findByEmail(request.email())
                .orElseThrow();

        String jwtToken = jwtService.generateTokenWithRolesAndPermissions(user);
        String refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return AuthenticationResponseDTO.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .id(user.getId()) // Include userId in response
                .build();
    }

    private void saveUserToken(User user, String jwtToken) {
        Token token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        List<Token> validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }
}
