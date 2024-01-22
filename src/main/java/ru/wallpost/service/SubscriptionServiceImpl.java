package ru.wallpost.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.wallpost.entity.User;
import ru.wallpost.repository.UserRepository;

import java.util.Optional;
import java.util.Set;

@Service
public class SubscriptionServiceImpl implements SubscriptionService{

    private final UserRepository userRepository;

    @Autowired
    public SubscriptionServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public Set<User> getAllUserSubscribers(long userId) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            return userOptional.get().getSubscribers();
        } else {
            throw new UsernameNotFoundException("Пользователь не найден");
        }
    }

    @Transactional(readOnly = true)
    @Override
    public Set<User> getAllMySubscribers() throws UsernameNotFoundException {
        User user = (User) AuthService.getAuthenticated();
        return getAllUserSubscribers(user.getId());
    }

    @Transactional(readOnly = true)
    @Override
    public Set<User> getAllUserSubscriptions(long userId) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            return userOptional.get().getSubscriptions();
        } else {
            throw new UsernameNotFoundException("Пользователь не найден");
        }
    }

    @Transactional(readOnly = true)
    @Override
    public Set<User> getAllMySubscriptions() throws UsernameNotFoundException {
        User user = (User) AuthService.getAuthenticated();
        return getAllUserSubscriptions(user.getId());
    }

    @Transactional
    @Override
    public void subscribe(long userId) throws UsernameNotFoundException {
        User user = (User) AuthService.getAuthenticated();
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent() && userOptional.get().getId() != user.getId()) {
            User newSubscription = userOptional.get();
            user.getSubscriptions().add(newSubscription);
            newSubscription.getSubscribers().add(user);
        } else {
            throw new UsernameNotFoundException("Пользователь не найден");
        }
    }

    @Transactional
    @Override
    public void unsubscribe(long userId) throws UsernameNotFoundException {
        User user = (User) AuthService.getAuthenticated();
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent() && user.getSubscriptions().contains(userOptional.get())) {
            User subscription = userOptional.get();
            user.getSubscriptions().remove(subscription);
            subscription.getSubscribers().remove(user);
        } else {
            throw new UsernameNotFoundException("Пользователь не найден");
        }
    }
}
