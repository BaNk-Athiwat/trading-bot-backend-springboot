package trading.trading_bot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import trading.trading_bot.entity.Role;
import trading.trading_bot.entity.User;
import trading.trading_bot.entity.enums.ERole;
import trading.trading_bot.model.UserDetailsModel;
import trading.trading_bot.model.request.SigninRequestModel;
import trading.trading_bot.model.request.SignupRequestModel;
import trading.trading_bot.model.response.JwtResponseModel;
import trading.trading_bot.repository.RoleRepository;
import trading.trading_bot.repository.UserRepository;
import trading.trading_bot.security.jwt.JwtTokenProvider;
import trading.trading_bot.service.AuthServiceInterface;

@Service
@Transactional
public class AuthServiceImpl implements AuthServiceInterface {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtProvider;

    @Override
    public UserDetailsModel signup(SignupRequestModel req) {

        if (userRepository.existsByUsername(req.getUsername())) {
            throw new RuntimeException("Username already exists.");
        }

        if (userRepository.existsByEmail(req.getEmail())) {
            throw new RuntimeException("Email already exists.");
            // ต้องเขียน custom exception.
            // throw new EmailAlreadyExistsException("Email already exists");
        }

        User user = new User();
        Role userRole;

        if (req.getRole().equals("user")) {
            userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        } else if (req.getRole().equals("admin")) {
            userRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        } else if (req.getRole().equals("founder")) {
            userRole = roleRepository.findByName(ERole.ROLE_FOUNDER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        } else {
            throw new RuntimeException("Error: Invalid role.");
        }

        user.setUsername(req.getUsername());
        user.setFirstName(req.getFirstName());
        user.setLastName(req.getLastName());
        user.setEmail(req.getEmail());
        user.setPassword(passwordEncoder.encode(req.getPassword()));
        user.setEnable(true);
        user.setRole(userRole);
        User saveUser = userRepository.save(user);
        UserDetailsModel result = new UserDetailsModel(saveUser);
        return result;
    }

    @Override
    public JwtResponseModel signin(SigninRequestModel req) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        return new JwtResponseModel(jwt);
    }
}
