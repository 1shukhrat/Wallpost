package ru.wallpost.DTO;

import lombok.Builder;

@Builder
public class UserProfileDTO {

    private long id;
    private String name;
    private String login;
    private String mood;
    private String avatar;
    private int subscribersCount;
    private int subscriptionsCount;


}
