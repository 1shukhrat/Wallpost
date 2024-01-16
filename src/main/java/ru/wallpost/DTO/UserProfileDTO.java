package ru.wallpost.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class UserProfileDTO {

    private long id;
    private String name;
    private String login;
    private String mood;
    private String avatar;
    private int subscribersCount;
    private int subscriptionsCount;


}
