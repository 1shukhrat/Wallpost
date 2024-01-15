package ru.wallpost.service;


import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.multipart.MultipartFile;
import ru.wallpost.DTO.UpdateInfoDTO;
import ru.wallpost.entity.User;

import java.io.IOException;

public interface ProfileService {

    User getByUserId(long id) throws UsernameNotFoundException;
    User me();
    User updateAvatar(MultipartFile multipartFile) throws IOException;
    User removeAvatar() throws IOException;
    User updateInfo(UpdateInfoDTO updateInfoDTO);
}
