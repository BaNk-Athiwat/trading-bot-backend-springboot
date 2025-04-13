package trading.trading_bot.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import trading.trading_bot.model.response.ApiResponseModel;
import trading.trading_bot.security.UserDetailsImpl;
import trading.trading_bot.service.BitkubServiceInterface;

@RestController
@RequestMapping("/api/v1/bitkub")
public class BitkubController {

    @Autowired
    private BitkubServiceInterface bitkubService;

    private UserDetailsImpl getUserDetailsImpl() {
        return (UserDetailsImpl) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }

    // @PreAuthorize("hasRole('ADMIN')")
    @GetMapping()
    public ResponseEntity<ApiResponseModel> status() {
        Object result = bitkubService.getStatus();
        ApiResponseModel res = new ApiResponseModel(200, "success", result);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/servertime")
    public ResponseEntity<ApiResponseModel> serverTime() {
        Object result = bitkubService.getServerTime();
        ApiResponseModel res = new ApiResponseModel(200, "success", result);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/market/ticker")
    public ResponseEntity<ApiResponseModel> marketTicker() {
        Object result = bitkubService.getMarketTicker();
        ApiResponseModel res = new ApiResponseModel(200, "success", result);
        return ResponseEntity.ok().body(res);
    }

    @GetMapping("/balances/{exchangeUuid}")
    public ResponseEntity<ApiResponseModel> balances(@PathVariable String exchangeUuid) {
        UUID userUuid = getUserDetailsImpl().getUserUuid();
        Object result = bitkubService.getBalances(userUuid, UUID.fromString(exchangeUuid));
        ApiResponseModel res = new ApiResponseModel(200, "success", result);
        return ResponseEntity.ok().body(res);
    }
}
