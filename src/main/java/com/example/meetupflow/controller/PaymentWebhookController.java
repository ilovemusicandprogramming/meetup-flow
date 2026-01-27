package com.example.meetupflow.controller;

import com.example.meetupflow.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/webhooks")
@RequiredArgsConstructor
@Slf4j
public class PaymentWebhookController {

    private final PaymentService paymentService;

    @PostMapping("/virtual-account")
    public ResponseEntity<String> handlePaymentWebhook(
            @RequestBody Map<String, String> webhookData) {

        log.info("=== 웹훅 수신 시작 ===");
        log.info("받은 데이터: {}", webhookData);

        try {
            // 필수 파라미터 검증
            String transactionId = webhookData.get("transactionId");
            String status = webhookData.get("status");

            if (transactionId == null || transactionId.isBlank()) {
                log.error("transactionId 누락");
                return ResponseEntity.badRequest().body("INVALID_TRANSACTION_ID");
            }

            if (status == null || status.isBlank()) {
                log.error("status 누락");
                return ResponseEntity.badRequest().body("INVALID_STATUS");
            }

            // 서비스로 위임
            paymentService.handleVirtualAccountWebhook(transactionId, status);

            log.info("=== 웹훅 처리 완료 ===");
            return ResponseEntity.ok("OK");

        } catch (IllegalArgumentException e) {
            log.error("웹훅 처리 실패 - 잘못된 요청: {}", e.getMessage());
            return ResponseEntity.badRequest().body("INVALID_REQUEST");

        } catch (Exception e) {
            log.error("웹훅 처리 중 예상치 못한 오류", e);
            return ResponseEntity.status(500).body("INTERNAL_SERVER_ERROR");
        }
    }
}
