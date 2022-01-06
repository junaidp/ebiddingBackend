package com.ebidding.resource;

import com.ebidding.model.*;
import com.ebidding.repository.CompanyRepository;
import com.ebidding.repository.ContractorRepository;
import com.ebidding.service.BidService;
import com.ebidding.service.CompanyService;
import com.ebidding.service.ProjectService;
import com.ebidding.service.UserService;
import com.google.gson.Gson;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/api")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EBiddingResource {

    @Inject
    ProjectService projectService;
    @Inject
    ContractorRepository contractorRepository;
    @Inject
    BidService bidService;
    @Inject
    CompanyService companyService;
    @Inject
    UserService userService;
    Gson gson = new Gson();

    @GET
    @Path("/hello")
    public String hello() {
        return "Hello RESTEasy";
    }

    @GET
    @Path("/saveProject")
    public String saveProject(@QueryParam("project") Project project) {
        return projectService.saveProject(project);
    }

    @GET
    @Path("/getProjects/{companyId}")
    public String getProjects(@PathParam("companyId") String companyId){
       return  projectService.getAllProjects(companyId);
    }

    @GET
    @Path("/getContractors/{companyId}")
    public String getContractors(@PathParam("companyId") String companyId){
        List<Contractor> companies = contractorRepository.findByCompany(companyId);
        return gson.toJson(companies) ;
    }

    @GET
    @Path("/saveContractor")
    public String saveContractor(@QueryParam("contractor") Contractor contractor)    {
        contractorRepository.persist(contractor);
        return "contractor saved";
    }

    @POST
    @Path("/saveBid")
    public String saveBid(@QueryParam("Bid") Bid bid){
         return bidService.saveBid(bid);
    }

    @GET
    @Path("/getBids/{companyId}")
    public String getBids(@PathParam("companyId") String companyId){
        return bidService.getBids(companyId);
    }

    @POST
    @Path("/saveCompany")
    public String saveCompany(@QueryParam("company") Company company) {
        return companyService.saveCompany(company);
    }

    @GET
    @Path("/saveUser")
    public String saveUser( @QueryParam("user") User user){
        return userService.saveUser(user);
    }

    @GET
    @Path("login/{userName}/{password}")
    public String login(@PathParam("userName") String userName,@PathParam("password") String password){
        return userService.login(userName, "password");
    }
}