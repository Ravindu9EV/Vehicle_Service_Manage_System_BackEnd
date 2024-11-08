package com.icet.crm.service;

public interface LoginService {
    boolean checkUserLogin(String email,String password);
    boolean checkAdminLogin(String email,String password);
}
