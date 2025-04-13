package trading.trading_bot.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import trading.trading_bot.model.KeyModel;
import trading.trading_bot.service.BitkubServiceInterface;
import trading.trading_bot.service.KeyServiceInterface;

@Service
public class BitkubServiceImpl implements BitkubServiceInterface {
    private static final String BASE_URL = "https://api.bitkub.com";
    private static final String STATUS_URL = "/api/status";
    private static final String SERVER_TIME_V3_URL = "/api/v3/servertime";
    private static final String MARKET_TICKER_ENDPOINT = "/api/v3/market/ticker";
    private static final String BALANCES_URL = "/api/v3/market/balances";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private KeyServiceInterface keyService;

    @Override
    public Object getStatus() {
        String url = BASE_URL + STATUS_URL;
        return restTemplate.getForObject(url, Object.class);
    }

    @Override
    public Object getServerTime() {
        String url = BASE_URL + SERVER_TIME_V3_URL;
        return restTemplate.getForObject(url, Object.class);
    }

    @Override
    public Object getMarketTicker() {
        String url = BASE_URL + MARKET_TICKER_ENDPOINT;
        return restTemplate.getForObject(url, Object.class);
    }

    @Override
    public Object getBalances(UUID userUuid, UUID exchangeUuid) {
        String url = BASE_URL + BALANCES_URL;
        Map<String, Object> payload = new HashMap<>();
        String timestamp = this.getServerTime().toString();
        KeyModel keyModel = keyService.getApiKey(userUuid, exchangeUuid);
        String msg = timestamp + "POST" + BALANCES_URL + payload;
        String signature = generateSignature(msg, keyModel.getSecretKey());

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");
        headers.set("Content-Type", "application/json");
        headers.set("X-BTK-APIKEY", keyModel.getApiKey());
        headers.set("X-BTK-TIMESTAMP", timestamp);
        headers.set("X-BTK-SIGN", signature);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(payload, headers);

        return restTemplate.exchange(
                url,
                HttpMethod.POST,
                entity,
                Object.class).getBody();
    }

    private String generateSignature(String message, String secretKey) {
        try {
            // สร้าง instance ของ Mac ที่ใช้ HMAC SHA-256
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");

            // สร้าง SecretKeySpec โดยใช้ API_SECRET และระบุว่าใช้ HMAC SHA-256
            SecretKeySpec secret_key = new SecretKeySpec(secretKey.getBytes(),
                    "HmacSHA256");

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
