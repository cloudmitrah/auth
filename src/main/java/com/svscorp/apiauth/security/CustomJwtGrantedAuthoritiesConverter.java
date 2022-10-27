package com.svscorp.apiauth.security;

import lombok.SneakyThrows;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomJwtGrantedAuthoritiesConverter implements Converter<Jwt, Collection<GrantedAuthority>> {
    JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
RoelsApi roelsApi=new RoelsApi();
Example example=new Example();
    @SneakyThrows
    @Override
    public Collection<GrantedAuthority> convert(Jwt source) {
        System.out.println(source);
        Collection<GrantedAuthority> grantedAuthorities = jwtGrantedAuthoritiesConverter.convert(source);
        grantedAuthorities.clear();
        List<String> roles=new ArrayList<>();
        roles=roelsApi.getRoles("12");

        //        Collection<String> scopes = source.getClaim("azp");
//        System.out.println(grantedAuthorities);
        for (String role : roles) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role));
        }
//        if (scopes.contains("read")) {
//            grantedAuthorities.add(new SimpleGrantedAuthority("Read"));
//        }
        System.out.println(grantedAuthorities);
        return grantedAuthorities;
    }
}