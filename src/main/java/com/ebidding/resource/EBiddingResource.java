package com.ebidding.resource;

import com.ebidding.dto.CompanyDTO;
import com.ebidding.model.*;
import com.ebidding.repository.ContractorRepository;
import com.ebidding.service.*;
import com.google.gson.Gson;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

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
    @Inject
    CommonService commonService;

    Gson gson = new Gson();

    @GET
    @Path("/hello")
    public String hello() {
        return "Hello RESTEasy";
    }

    @POST
    @Path("/saveProject")
    public String saveProject(@RequestBody Project project) {
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

    @POST
    @Path("/saveContractor")
    public String saveContractor(@RequestBody Contractor contractor)    {
        System.out.println(contractor);
        contractorRepository.persist(contractor);
        return "contractor saved";
    }

    @POST
    @Path("/saveBid")
    public String saveBid(@RequestBody Bid bid){
         return bidService.saveBid(bid);
    }

    @GET
    @Path("/getBids/{companyId}")
    public String getBids(@PathParam("companyId") String companyId){

        return bidService.getBids(companyId);
    }

    @POST
    @Path("/saveCompany")
    public String saveCompany(@RequestBody CompanyDTO companyDto) {
        return commonService.saveCompanyandItsAdmin(companyDto);
    }

    @POST
    @Path("/saveUser")
    public String saveUser( @RequestBody User user){
        return userService.saveUser(user);
    }

    @GET
    @Path("login/{userName}/{password}")
    public String login(@PathParam("userName") String userName,@PathParam("password") String password){
        return userService.login(userName, "password");
    }

    @POST
    @Path("/saveBidding")
    public String saveBidding(@RequestBody Bidding bidding){
        return bidService.saveBidding(bidding);
    }

    @GET
    @Path("getBiddings/{bidId}")
    public String getBiddings(@PathParam("bidId") String bidId){
        return bidService.getBiddings(bidId);
    }
}