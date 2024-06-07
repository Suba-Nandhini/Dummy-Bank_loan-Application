package com.suba.Bankloanapplication.model;

import java.util.Arrays;
import java.util.List;

public enum Role {
    ADMIN(Arrays.asList(
            Permissions.GRANT_ADMIN_LOAN,

            Permissions.APPROVE_LOAN,
            Permissions.GET_LIST,
            Permissions.SEND_MAIL,
            Permissions.REQUEST_DELETION,
            Permissions.APPROVE_DELETION,
            Permissions.VIEW_PENDING_DELETIONS
    )),
    CUSTOMER(Arrays.asList(
            Permissions.VISIT,
            Permissions.GET_LOAN,
            Permissions.REQUEST_DELETION,
            Permissions.CREATE_ACCOUNT
    )),
    SUPER_ADMIN(Arrays.asList(
            Permissions.ASSIGN_ROLE,
            Permissions.GRANT_ADMIN_LOAN,

            Permissions.APPROVE_LOAN,
            Permissions.GET_LIST,
            Permissions.SEND_MAIL,
            Permissions.REQUEST_DELETION,
            Permissions.APPROVE_DELETION,
            Permissions.VIEW_PENDING_DELETIONS

    ));
    private List<Permissions> permissions;
    public List<Permissions> getPermissions(){
        return permissions;
    }
    public void setPermissions(List<Permissions> permissions){
        this.permissions = permissions;
    }
    Role(List<Permissions> permissions){
        this.permissions = permissions;
    }

}
