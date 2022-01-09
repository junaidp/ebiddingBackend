package com.ebidding.service;

import com.ebidding.model.ErrorInfo;
import com.ebidding.model.Project;
import com.ebidding.repository.ProjectRepository;
import com.google.gson.Gson;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class ProjectService {

    @Inject
    ProjectRepository projectRepository;

    Gson gson = new Gson();

    public String getAllProjects(String companyId){
        Gson gson = new Gson();
        List<Project> projects = Project.find("companyId", companyId).list();
        return gson.toJson(projects);
    }

    public String saveProject(Project project) {
        try{
            project.setProjectId(UUID.randomUUID().toString());
            project.persist();
            ErrorInfo errorInfo = new ErrorInfo(true, "project saved");
            return gson.toJson(errorInfo);
        }catch(Exception ex){
            ErrorInfo errorInfo = new ErrorInfo(false, "project not saved: "+ ex);
            return gson.toJson(errorInfo);
        }

    }
}
