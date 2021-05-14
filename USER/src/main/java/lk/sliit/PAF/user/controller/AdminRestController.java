package lk.sliit.PAF.user.controller;

import lk.sliit.PAF.user.dto.AdminBuyerDTO;
import lk.sliit.PAF.user.dto.AdminResearcherDTO;
import lk.sliit.PAF.user.model.AdminModel;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

@Path("/admin")
public class AdminRestController {
    AdminModel adminModel = new AdminModel();

    // load all researcher account details
    @GET
    @Path("/researcher")
    @Produces(MediaType.APPLICATION_JSON)
    public List<AdminResearcherDTO> loadAdminResearcher() throws SQLException {
        return adminModel.findAllResearchers();
    }

    // delete researcher account
    @DELETE
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteResearcher(@PathParam("id")int id) throws SQLException {
        if(adminModel.deleteResearcher(id)){
            return Response.ok().build();
        }
        else{
            return Response.notModified().build();
        }
    }

    // load all buyer account details
    @GET
    @Path("/buyer")
    @Produces(MediaType.APPLICATION_JSON)
    public List<AdminBuyerDTO> loadAdminBuyer() throws SQLException {
        return adminModel.findAllBuyers();
    }

    // delete buyer account
    @DELETE
    @Path("/deleteBuyer/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteBuyer(@PathParam("id") int id) throws SQLException {
        if(adminModel.deleteBuyer(id)){
            return Response.ok().build();
        }
        else{
            return Response.notModified().build();
        }
    }
}
