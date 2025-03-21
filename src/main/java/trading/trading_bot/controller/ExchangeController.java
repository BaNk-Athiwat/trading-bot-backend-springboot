package trading.trading_bot.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import trading.trading_bot.model.ExchangeModel;
import trading.trading_bot.model.response.ApiResponseModel;
import trading.trading_bot.security.UserDetailsImpl;
import trading.trading_bot.service.ExchangeServiceInterface;

@RestController
@RequestMapping("/api/v1/exchange")
public class ExchangeController {

    @Autowired
    private ExchangeServiceInterface exchangeService;

    @GetMapping("/connections")
    public ResponseEntity<?> getAllExchangeConnection() {
        UserDetailsImpl userDetailsImpl = (UserDetailsImpl) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        UUID userUuid = userDetailsImpl.getUserUuid();

        List<ExchangeModel> result = exchangeService.getAllExchangeConnection(userUuid);
        ApiResponseModel res = new ApiResponseModel(200, "success", result);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/test")
    public ResponseEntity<?> test() {

        return ResponseEntity.ok(new ApiResponseModel(200, "success", new ExchangeModel()));
    }

}
