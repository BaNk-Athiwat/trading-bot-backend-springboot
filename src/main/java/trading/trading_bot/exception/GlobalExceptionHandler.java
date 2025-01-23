package trading.trading_bot.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
        // Map<String, Object> body = new HashMap<>();
        // body.put("timestamp", new Date());
        // body.put("message", "เกิดข้อผิดพลาดในระบบ");
        // body.put("error", ex.getMessage());

        // return ResponseEntity
        // .status(HttpStatus.INTERNAL_SERVER_ERROR)
        // .body(body);
    }
}
