package trading.trading_bot.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import trading.trading_bot.model.KeyViewModel;
import trading.trading_bot.service.TradingServiceInterface;

@Service
public class TradingServiceImpl implements TradingServiceInterface {

    private static final String BASE_URL = "https://api.bitkub.com";
    private static final String STATUS_URL = "/api/status";
    private static final String MARKET_TICKER_ENDPOINT = "/api/market/ticker";
    private static final String SERVER_TIME_V3_URL = "/api/v3/servertime";
    private static final String BALANCES_URL = "/api/v3/market/balances";
    private static final String WALLET_URL = "/api/v3/market/wallet";

    private String API_KEY = "";
    private String API_SECRET = "";

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Boolean createConnection(KeyViewModel req) {
        System.out.println("API_KEY: " + API_KEY);
        System.out.println("API_SECRET: " + API_SECRET);
        return true;
    }

    @Override
    public String getStatusData() {
        String url = BASE_URL + STATUS_URL;
        return restTemplate.getForObject(url, String.class);
    }

    @Override
    public String getMarketTicker() {
        String url = BASE_URL + MARKET_TICKER_ENDPOINT;
        var res = restTemplate.getForObject(url, String.class);
        System.out.println(res);
        // return restTemplate.getForObject(url, String.class);
        return res;
    }

    @Override
    public String getServerTimeData() {
        String url = BASE_URL + SERVER_TIME_V3_URL;
        return restTemplate.getForObject(url, String.class);
    }

    @Override
    public String getBalancesData() {
        String url = BASE_URL + BALANCES_URL;
        return restTemplate.getForObject(url, String.class);
    }

    @Override
    public String getWalletData() {
        System.out.println("API_KEY1: " + API_KEY);
        System.out.println("API_SECRET1: " + API_SECRET);
        String timestamp = getServerTimeV3();
        String method = "POST";
        Map<String, Object> payload = new HashMap<>();
        String message = timestamp + method + WALLET_URL + payload;
        String signature = generateSignature(message);
        System.out.println(
                "Timestamp: " + timestamp + ", Method: " + method + ", Payload: " + payload + ", Message: " + message);
        System.out.println("Signature: " + signature);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");
        headers.set("Content-Type", "application/json");
        headers.set("X-BTK-APIKEY", API_KEY);
        headers.set("X-BTK-TIMESTAMP", timestamp);
        headers.set("X-BTK-SIGN", signature);

        // HttpEntity<String> entity = new HttpEntity<>(payload, headers);
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(payload, headers);
        String url = BASE_URL + WALLET_URL;

        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                entity,
                String.class);

        return response.getBody();
    }

    private String getServerTimeV3() {
        String url = BASE_URL + SERVER_TIME_V3_URL;
        String response = restTemplate.getForObject(url, String.class);
        return response;
    }

    private String generateSignature(String message) {
        try {
            // สร้าง instance ของ Mac ที่ใช้ HMAC SHA-256
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");

            // สร้าง SecretKeySpec โดยใช้ API_SECRET และระบุว่าใช้ HMAC SHA-256
            SecretKeySpec secret_key = new SecretKeySpec(API_SECRET.getBytes(), "HmacSHA256");

            // กำหนด secret key ให้กับ Mac instance
            sha256_HMAC.init(secret_key);

            // ทำการเข้ารหัสข้อความ (message) และเก็บผลลัพธ์ในรูปแบบ byte array
            byte[] hash = sha256_HMAC.doFinal(message.getBytes());

            // แปลง byte array เป็น string ในรูปแบบ hexadecimal
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }

            // ส่งคืน string ที่เป็นผลลัพธ์ของการเข้ารหัส
            return hexString.toString();
        } catch (Exception e) {
            // ถ้ามีข้อผิดพลาดเกิดขึ้น ให้โยน RuntimeException พร้อมกับข้อความข้อผิดพลาด
            throw new RuntimeException("Unable to generate signature", e);
        }
    }
}
