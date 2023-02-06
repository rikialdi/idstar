package com.idstar.apps.service;

import com.idstar.apps.dto.LoginModel;
import com.idstar.apps.dto.RegisterModel;

import java.security.Principal;
import java.util.Map;

public interface UserService {
    public Map login(LoginModel objLogin);
    Map registerManual(RegisterModel objModel) ;

    public Map getDetailProfile(Principal principal);
}
