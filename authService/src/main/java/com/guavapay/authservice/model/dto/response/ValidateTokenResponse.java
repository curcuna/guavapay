package com.guavapay.authservice.model.dto.response;

import com.guavapay.authservice.model.entity.Role;

import java.util.Set;

public class ValidateTokenResponse {

        private Set<Role> roleSet;

        public Set<Role> getRoleSet() {
                return roleSet;
        }

        public void setRoleSet(Set<Role> roleSet) {
                this.roleSet = roleSet;
        }
}
