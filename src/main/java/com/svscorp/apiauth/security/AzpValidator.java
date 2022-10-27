package com.svscorp.apiauth.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2ErrorCodes;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.util.Assert;
import org.springframework.web.servlet.handler.UserRoleAuthorizationInterceptor;

import java.util.List;

public class AzpValidator implements OAuth2TokenValidator<Jwt> {

    private final String azp;
    Users users=new Users();
    AzpValidator(String azp) {
        Assert.hasText(azp, "azp is null or empty");
        this.azp = azp;
    }

    public OAuth2TokenValidatorResult validate(Jwt jwt) {
        users.setClientId(jwt.getClaimAsString("azp"));
        String azps=jwt.getClaimAsString("azp");
        System.out.println(azps);
        if (azps.contains(this.azp)) {
            return OAuth2TokenValidatorResult.success();
        }
        OAuth2Error err = new OAuth2Error(OAuth2ErrorCodes.INVALID_TOKEN);
        return OAuth2TokenValidatorResult.failure(err);
    }
}
