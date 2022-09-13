package com.guavapay.authservice.model.dto.response;

import com.guavapay.authservice.model.entity.Person;
import com.guavapay.authservice.model.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {

        private String token;
        private Person person;
}
