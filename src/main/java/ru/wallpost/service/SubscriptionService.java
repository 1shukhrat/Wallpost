package ru.wallpost.service;


import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.wallpost.entity.User;


import java.util.Set;

public interface SubscriptionService {

    Set<User> getAllUserSubscribers(long userId) throws UsernameNotFoundException;
    Set<User> getAllMySubscribers() throws UsernameNotFoundException;
    Set<User> getAllUserSubscriptions(long userId) throws UsernameNotFoundException;
    Set<User> getAllMySubscriptions() throws UsernameNotFoundException;

    void subscribe(long userId) throws UsernameNotFoundException;

    void unsubscribe(long userId) throws UsernameNotFoundException;
}
