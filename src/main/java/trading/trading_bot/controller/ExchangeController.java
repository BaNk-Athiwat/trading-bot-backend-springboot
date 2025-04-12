package trading.trading_bot.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    private UserDetailsImpl getUserDetailsImpl() {
        return (UserDetailsImpl) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }

    @GetMapping("/exchange-list")
    public ResponseEntity<?> getExchangeList() {
        UUID userUuid = getUserDetailsImpl().getUserUuid();
        List<ExchangeModel> result = exchangeService.getExchangeList(userUuid);
        ApiResponseModel res = new ApiResponseModel(200, "success", result);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/status/{exchangeUuid}")
    public ResponseEntity<?> getExchangeStatus(@PathVariable String exchangeUuid) {
        UUID userUuid = getUserDetailsImpl().getUserUuid();
        ExchangeModel result = exchangeService.getExchangeStatus(userUuid, exchangeUuid);
        ApiResponseModel res = new ApiResponseModel(200, "success", result);
        return ResponseEntity.ok(res);
    }

}
