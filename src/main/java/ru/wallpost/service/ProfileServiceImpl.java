package ru.wallpost.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.wallpost.DTO.UpdateInfoDTO;
import ru.wallpost.entity.User;
import ru.wallpost.exception.UserAlreadyExistsException;
import ru.wallpost.repository.UserRepository;
import ru.wallpost.util.FileUtil;

import java.io.IOException;
import java.util.Optional;

@Service
public class ProfileServiceImpl implements ProfileService{

    private final UserRepository userRepository;

    @Autowired
    public ProfileServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public User getByUserId(long id) throws UsernameNotFoundException {
        return userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));

    }

    @Transactional(readOnly = true)
    @Override
    public User me() {
        User user = userRepository.findByLogin(AuthService.getAuthenticated().getUsername()).get();
        return getByUserId(user.getId());
    }

    @Transactional
    @Override
    public User updateAvatar(MultipartFile multipartFile) throws IOException {
        User user = userRepository.findByLogin(AuthService.getAuthenticated().getUsername()).get();
        String link = user.getAvatarLink();
        if (!user.getAvatarLink().equals(FileUtil.DEFAULT_AVATAR)) {
            FileUtil.remove(link.substring(link.lastIndexOf("/") + 1));}
        user.setAvatarLink(FileUtil.save(multipartFile));
        return userRepository.save(user);
    }

    @Transactional
    @Override
    public User removeAvatar() throws IOException {
        User user = userRepository.findByLogin(AuthService.getAuthenticated().getUsername()).get();
        String link = user.getAvatarLink();
        if (!user.getAvatarLink().equals(FileUtil.DEFAULT_AVATAR)) {
            FileUtil.remove(link.substring(link.lastIndexOf("/") + 1));
        }
        user.setAvatarLink(FileUtil.DEFAULT_AVATAR);
        return userRepository.save(user);
    }

    @Transactional
    @Override
    public User updateInfo(UpdateInfoDTO updateInfoDTO) throws UserAlreadyExistsException {
        User user = userRepository.findByLogin(AuthService.getAuthenticated().getUsername()).get();
        Optional<User> checkedUser = userRepository.findByLogin(updateInfoDTO.getLogin());
        if (checkedUser.isPresent() && !checkedUser.get().getLogin().equals(user.getLogin())) {
            throw new UserAlreadyExistsException(checkedUser.get().getLogin());
        }
        user.setLogin(updateInfoDTO.getLogin());
        user.setName(updateInfoDTO.getName());
        user.setMood(updateInfoDTO.getMood());
        return userRepository.save(user);
    }
}
