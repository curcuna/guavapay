package com.guavapay.gateway.bean.dto.response;


import com.guavapay.gateway.bean.model.Role;
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
