package com.ebidding.service;

import com.ebidding.model.ErrorInfo;
import com.ebidding.model.User;
import com.google.gson.Gson;
import javax.enterprise.context.ApplicationScoped;
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
        User user = User.find("email",email).singleResult();
        return gson.toJson(user);
    }
}
