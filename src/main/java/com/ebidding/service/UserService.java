package com.ebidding.service;

import com.ebidding.model.User;
import com.google.gson.Gson;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserService {

    Gson gson = new Gson();

    public String saveUser(User user){
        try {
            user.persist();
            return gson.toJson("user saved");
        }catch(Exception ex){
            return gson.toJson("Error in saving user:" + ex);
        }
    }

    public String login(String email, String password) {
        User user = User.find("email",email).singleResult();
        return gson.toJson(user);
    }
}
