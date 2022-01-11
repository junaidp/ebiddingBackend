package com.ebidding.resource;

import com.ebidding.dto.CompanyDTO;
import com.ebidding.model.*;
import com.ebidding.repository.ContractorRepository;
import com.ebidding.service.*;
import com.ebidding.util.Utility;
import com.google.gson.Gson;
import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import io.smallrye.common.annotation.Blocking;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.jboss.resteasy.annotations.Query;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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

    @Inject
    Mailer mailer ;

    @Inject
     Utility utility;

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

    @GET
    @Path("/getBid/{bidId}")
    public String getBid(@PathParam("bidId") String bidId){

        return bidService.getBid(bidId);
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
    @Path("login")
    public String login(@QueryParam("userName") String userName,@QueryParam("password") String password){
        return userService.login(userName, password);
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

    @GET
    @Path("getBiddingResults")
    public String getBiddingResults(@QueryParam("bidId") String bidId , @QueryParam("contractorId") String contractorId){

        return bidService.getBiddingResults(bidId, contractorId);
    }

    @GET
    @Path("sendEmail")
    @Blocking
    public String sendEmail() {


        utility.sendEmail("junaidp@gmail.com", "Bidding", "https://ebidding.herokuapp.com/bidding/" + 1 + ":" + 2);
        return "email sent";
    }
//       Mail mail = Mail.withText("junaidp@gmail.com","dd","dd");
//        mailer.send(mail);
//        return "mail sent";


}