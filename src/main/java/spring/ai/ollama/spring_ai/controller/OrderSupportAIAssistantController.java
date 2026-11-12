package spring.ai.ollama.spring_ai.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import spring.ai.ollama.spring_ai.service.OrderSupportAIAssistantService;

@RestController
@RequestMapping("/api/order-support")
public class OrderSupportAIAssistantController {

    private final OrderSupportAIAssistantService orderSupportAIAssistantService;

    public OrderSupportAIAssistantController(
            OrderSupportAIAssistantService orderSupportAIAssistantService) {
        this.orderSupportAIAssistantService = orderSupportAIAssistantService;
    }

    @GetMapping("/assist")
    public ResponseEntity<String> assistWithOrderSupport(
            @RequestParam String customerName,
            @RequestParam String orderId,
            @RequestParam String customerMessage) {
        return ResponseEntity.ok(orderSupportAIAssistantService.assistWithOrderSupport(
                customerName, orderId, customerMessage));
    }
}
