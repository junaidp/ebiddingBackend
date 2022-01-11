package com.ebidding.service;

import com.ebidding.model.ErrorInfo;
import com.ebidding.model.User;
import com.google.gson.Gson;
import javax.enterprise.context.ApplicationScoped;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@ApplicationScoped
public class UserService {

    Gson gson = new Gson();

    public String saveUser(User user){
        try {
            user.setUserId(UUID.randomUUID().toString());
            user.persist();
            ErrorInfo errorInfo = new ErrorInfo(true, "user saved");
            return gson.toJson(errorInfo);

        }catch(Exception ex){
            ErrorInfo errorInfo = new ErrorInfo(false, "Error in saving user:" + ex);
            return gson.toJson(errorInfo);
        }
    }

    public String login(String email, String password) {
       try {
           Map<String, Object> params = new HashMap<>();
           params.put("email", email);
           params.put("password", password);
           User user = User.find("email = :email and password = :password", params).firstResult();
           return gson.toJson(user);
       }catch(Exception ex){
           ErrorInfo error = new ErrorInfo(true, "Error signingIn:" + ex);
           return gson.toJson(error);
       }
    }
}
