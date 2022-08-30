package com.DietfriendsTodoDemoApp.security.util;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.UUID;

public class SecurityUtil {
    public static String getLoginUsername() {
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return user.getUsername();
    }
}
