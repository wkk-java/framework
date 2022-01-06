package com.wk.oauth.entity;

import lombok.Data;

import java.util.UUID;


@Data
public class Permission {

    private UUID id;

    private String permission;

    private String description;
}
