package com.ebidding.service;

import com.ebidding.model.Project;
import com.google.gson.Gson;
import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class ProjectService {

    public String getAllProjects(String companyId){
        Gson gson = new Gson();
        List<Project> projects = Project.find("companyId", companyId).list();
        return gson.toJson(projects);
    }

    public String saveProject(Project project) {
        try{
            project.persist();
            return "project saved";
        }catch(Exception ex){
            return "Project not saved: " + ex;
        }

    }
}
