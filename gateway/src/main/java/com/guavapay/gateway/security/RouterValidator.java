package com.guavapay.gateway.security;

import com.guavapay.gateway.bean.model.Role;
import com.guavapay.gateway.util.Consts;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.util.UrlPathHelper;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Component
public class RouterValidator {

    public static final List<String> openApiEndpointList = List.of(
            Consts.URL_AUTH_LOGIN, Consts.URL_AUTH_SIGNUP
    );
    public static final Map<String, Set<Role>> endpointRole = Map.of(
            Consts.URL_ORDER, Set.of(Role.ROLE_USER),
            Consts.URL_ORDER + Consts.URL_ORDER_PERSON, Set.of(Role.ROLE_USER),
            Consts.URL_ORDER + Consts.URL_ORDER_DESTINATION, Set.of(Role.ROLE_USER),

            Consts.URL_AUTH + Consts.URL_AUTH_SIGNUP_COURIER, Set.of(Role.ROLE_ADMIN),
            Consts.URL_AUTH + Consts.URL_AUTH_COURIER, Set.of(Role.ROLE_ADMIN),
            Consts.URL_ORDER + Consts.URL_ORDER_ADMIN, Set.of(Role.ROLE_ADMIN),
            Consts.URL_ORDER + Consts.URL_ORDER_STATUS, Set.of(Role.ROLE_ADMIN,Role.ROLE_COURIER),
            Consts.URL_CARGO, Set.of(Role.ROLE_ADMIN),

            Consts.URL_CARGO + Consts.URL_CARGO_PERSON, Set.of(Role.ROLE_COURIER),
            Consts.URL_CARGO + Consts.URL_CARGO_STATUS, Set.of(Role.ROLE_COURIER)

    );

    public Predicate<ServerHttpRequest> isSecured =
            request -> openApiEndpointList
                    .stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri));

    public BiPredicate<ServerHttpRequest, Set<Role>> hasAccessRight =
            (request, roleSet) -> {
                String decodedUrl = Arrays.stream(request.getURI().getPath().split("/")).filter(f -> !f.matches("-?\\d+(\\.\\d+)?")).collect(Collectors.joining("/"));
                if(roleSet.stream().filter(i->endpointRole.get(decodedUrl).contains(i)).count()> 0){
                    return true;
                }
                return false;
            };

}