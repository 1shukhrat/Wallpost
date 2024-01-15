package ru.wallpost.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.wallpost.DTO.UpdateInfoDTO;
import ru.wallpost.entity.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.wallpost.DTO.UserProfileDTO;
import ru.wallpost.mapper.UserMapper;
import ru.wallpost.service.ProfileService;

import java.io.IOException;

@RestController
@RequestMapping("/api/profiles")
public class ProfileController {

    private final ProfileService profileService;

    @Autowired
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserProfileDTO> get(@PathVariable("id") long userId) throws UsernameNotFoundException {
        User user = profileService.getByUserId(userId);
        return new ResponseEntity<>(UserMapper.toProfileDTO(user), HttpStatus.OK);
    }

    @GetMapping("/me")
    public ResponseEntity<UserProfileDTO> get() throws UsernameNotFoundException {
        User user = profileService.me();
        return new ResponseEntity<>(UserMapper.toProfileDTO(user), HttpStatus.OK);
    }

    @PatchMapping("/edit")
    public ResponseEntity<UserProfileDTO> edit(@RequestBody UpdateInfoDTO updateInfoDTO) {
        User user = profileService.updateInfo(updateInfoDTO);
        return new ResponseEntity<>(UserMapper.toProfileDTO(user), HttpStatus.OK);
    }

    @PatchMapping("/updateAvatar")
    public ResponseEntity<UserProfileDTO> edit(@RequestParam("avatar") MultipartFile multipartFile) throws IOException {
        User user = profileService.updateAvatar(multipartFile);
        return new ResponseEntity<>(UserMapper.toProfileDTO(user), HttpStatus.OK);
    }

    @PatchMapping("/removeAvatar")
    public ResponseEntity<UserProfileDTO> remove() throws IOException {
        User user = profileService.removeAvatar();
        return new ResponseEntity<>(UserMapper.toProfileDTO(user), HttpStatus.OK);
    }
}
