package com.svscorp.apiauth.service;

import org.springframework.stereotype.Service;

@Service
public class ApiService {
    public String getPublicString() {
        return "This is a public String.";
    }

    public String getProtectedString() {
        return "This is a protected String.";
    }

    public String getAdminString() {
        return "This is an admin String.";
    }

}
