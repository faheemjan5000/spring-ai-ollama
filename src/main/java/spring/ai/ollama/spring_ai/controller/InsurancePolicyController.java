package spring.ai.ollama.spring_ai.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import spring.ai.ollama.spring_ai.service.MessageRolesDemoService;

@RestController
@RequestMapping("/api/insurance-policy")
public class InsurancePolicyController {

    private MessageRolesDemoService messageRolesDemoService;

    public InsurancePolicyController(MessageRolesDemoService messageRolesDemoService){
        this.messageRolesDemoService = messageRolesDemoService;
    }

    @GetMapping("/check")
    public ResponseEntity<String> checkPolicy(@RequestParam String message){
        return ResponseEntity.ok(messageRolesDemoService.checkPolicy2(message));
    }
}
