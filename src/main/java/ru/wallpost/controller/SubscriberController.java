package ru.wallpost.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.wallpost.DTO.UserDTO;
import ru.wallpost.mapper.UserMapper;
import ru.wallpost.service.SubscriptionService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/subscribers")
public class SubscriberController {

    private final SubscriptionService subscriptionService;

    @Autowired
    public SubscriberController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllSubscribers(@RequestParam(value = "userId", required = false)
                                                             Optional<Long> userId) throws UsernameNotFoundException {
        List<UserDTO> subscribers;
        if (userId.isPresent()) {
            subscribers = UserMapper.toDTO(subscriptionService.getAllUserSubscribers(userId.get()));
        } else {
            subscribers = UserMapper.toDTO(subscriptionService.getAllMySubscribers());
        }
        return new ResponseEntity<>(subscribers, HttpStatus.OK);
    }
}
