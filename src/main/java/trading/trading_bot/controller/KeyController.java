package trading.trading_bot.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import trading.trading_bot.model.KeyModel;
import trading.trading_bot.model.response.ApiResponseModel;
import trading.trading_bot.security.UserDetailsImpl;
import trading.trading_bot.service.KeyServiceInterface;

@RestController
@RequestMapping("/api/v1/key")
public class KeyController {

    @Autowired
    KeyServiceInterface keyService;

    private UserDetailsImpl getUserDetailsImpl() {
        return (UserDetailsImpl) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }

    @GetMapping("/api-key/{exchangeUuid}")
    public ResponseEntity<?> getApiKey(@PathVariable String exchangeUuid) {
        UUID userUuid = getUserDetailsImpl().getUserUuid();
        KeyModel result = keyService.getApiKey(userUuid, UUID.fromString(exchangeUuid));
        ApiResponseModel res = new ApiResponseModel(200, "success", result != null ? true : false);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/api-key")
    public ResponseEntity<?> insertApiKey(@RequestBody KeyModel keyModel) {
        UUID userUuid = getUserDetailsImpl().getUserUuid();
        keyModel.setUserUuid(userUuid.toString());
        KeyModel result = keyService.insertApiKey(keyModel);
        ApiResponseModel res = new ApiResponseModel(200, "success", result != null ? true : false);
        return ResponseEntity.ok(res);
    }

    @PutMapping("/api-key")
    public ResponseEntity<?> updateApiKey(@RequestBody KeyModel keyModel) {
        UUID userUuid = getUserDetailsImpl().getUserUuid();
        keyModel.setUserUuid(userUuid.toString());
        KeyModel result = keyService.updateApiKey(keyModel);
        ApiResponseModel res = new ApiResponseModel(200, "success", result != null ? true : false);
        return ResponseEntity.ok(res);
    }

}
