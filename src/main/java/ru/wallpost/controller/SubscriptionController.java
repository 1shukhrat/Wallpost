package ru.wallpost.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import ru.wallpost.DTO.UserDTO;
import ru.wallpost.mapper.UserMapper;
import ru.wallpost.service.SubscriptionService;
import ru.wallpost.util.MessageResponse;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @Autowired
    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllSubscriptions(@RequestParam(value = "userId", required = false)
                                                       Optional<Long> userId) throws UsernameNotFoundException {
        List<UserDTO> subscriptions;
        if (userId.isPresent()) {
            subscriptions = UserMapper.toDTO(subscriptionService.getAllUserSubscriptions(userId.get()));
        } else {
            subscriptions = UserMapper.toDTO(subscriptionService.getAllMySubscriptions());
        }
        return new ResponseEntity<>(subscriptions, HttpStatus.OK);
    }

    @PatchMapping("/subscribe")
    public ResponseEntity<MessageResponse> add(@RequestParam("userId") long userId) throws UsernameNotFoundException{
        subscriptionService.subscribe(userId);
        return new ResponseEntity<>(new MessageResponse("Пользователь успешно добавлен в подписки",
                LocalDateTime.now()),
                HttpStatus.OK);
    }

    @PatchMapping("/unsubscribe")
    public ResponseEntity<MessageResponse> remove(@RequestParam("userId") long userId) throws UsernameNotFoundException{
        subscriptionService.unsubscribe(userId);
        return new ResponseEntity<>(new MessageResponse("Пользователь успешно удален из подписок",
                LocalDateTime.now()),
                HttpStatus.OK);
    }
}
