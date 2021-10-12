package by.htp.chatter.controller;

import by.htp.chatter.model.User;
import by.htp.chatter.repositry.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

@Controller
@RequestMapping("/")
public class MainController {
    private final MessageRepository messageRepository;

    @Value("${spring.profiles.active}")
    private String profile;

    @Autowired
    public MainController(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @GetMapping
    public String main(
            Model model,
            @AuthenticationPrincipal User user
    ) {
        HashMap<Object, Object> attributeValue = new HashMap<>();
        if (user != null) {
            attributeValue.put("profile", user);
            attributeValue.put("messages", messageRepository.findAll());
        }

        model.addAttribute("frontendData", attributeValue);
        model.addAttribute("isDevMode", "dev".equals(profile));

        return "index";
    }
}
