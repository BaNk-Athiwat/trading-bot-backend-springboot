package trading.trading_bot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import trading.trading_bot.model.KeyViewModel;
import trading.trading_bot.model.SuccessResponseViewModel;
import trading.trading_bot.service.TradingServiceInterface;

@RestController
@RequestMapping("/api/v1")
public class TradingController {

    @Autowired
    private TradingServiceInterface tradingService;

    @GetMapping("/key-connection")
    public ResponseEntity<SuccessResponseViewModel> createConnection(KeyViewModel req) {
        Boolean result = tradingService.createConnection(req);
        SuccessResponseViewModel res = new SuccessResponseViewModel("Connection is successful");
        return ResponseEntity.ok().body(res);
    }

    @GetMapping("/status")
    public String status() {
        return tradingService.getStatusData();
    }

    @GetMapping("/market/ticker")
    public ResponseEntity<String> marketTicker() {
        var res = tradingService.getMarketTicker();
        return ResponseEntity.ok().body(res);
    }

    @GetMapping("/servertime")
    public String serverTime() {
        return tradingService.getServerTimeData();
    }

    @GetMapping("/balances")
    public String balances() {
        return tradingService.getBalancesData();
    }

    @PostMapping("/wallet")
    public String wallet() {
        return tradingService.getWalletData();
    }
}
