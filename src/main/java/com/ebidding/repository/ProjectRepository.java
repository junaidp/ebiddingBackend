package com.ebidding.repository;

import com.ebidding.model.Project;
import io.quarkus.mongodb.panache.PanacheMongoRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProjectRepository implements PanacheMongoRepository<Project> {


}
