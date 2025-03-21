package trading.trading_bot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import trading.trading_bot.model.UserModel;
import trading.trading_bot.model.request.SigninRequestModel;
import trading.trading_bot.model.request.SignupRequestModel;
import trading.trading_bot.model.response.ApiResponseModel;
import trading.trading_bot.model.response.JwtResponseModel;
import trading.trading_bot.service.AuthServiceInterface;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthServiceInterface authService;

    @PostMapping("/signup")
    public ResponseEntity<?> register(@RequestBody SignupRequestModel req) {
        UserModel result = authService.signup(req);
        ApiResponseModel res = new ApiResponseModel();
        res.setStatus(200);
        res.setMessage("User registered successfully.");
        res.setData(result);
        return ResponseEntity.ok().body(res);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> login(@RequestBody SigninRequestModel req) {
        JwtResponseModel result = authService.signin(req);
        ApiResponseModel res = new ApiResponseModel();
        res.setStatus(200);
        res.setMessage("User logined successfully.");
        res.setData(result);
        return ResponseEntity.ok().body(res);
    }

}
