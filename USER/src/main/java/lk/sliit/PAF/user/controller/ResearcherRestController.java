package lk.sliit.PAF.user.controller;

import lk.sliit.PAF.user.dto.ResearcherDTO;
import lk.sliit.PAF.user.model.ResearcherModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;


@Path("/researcher")
public class ResearcherRestController {
    ResearcherModel researcherModel = ResearcherModel.getInstance();
    ResearcherModel researcherObject = new ResearcherModel();

    //save a researcher
    @POST
    @Path("/saveResearcher")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response addResearcher(ResearcherDTO researcherDTO, @Context HttpServletRequest request) throws Exception {
        int r = researcherObject.insertResearcherDetail(
                researcherDTO.getfName(),
                researcherDTO.getlName(),
                researcherDTO.getEmail(),
                researcherDTO.getContactNo(),
                researcherDTO.getAddress(),
                researcherDTO.getZipCode(),
                researcherDTO.getRate(),
                researcherDTO.getPassword()
        );
        // save the researcher id in the session

        HttpSession session= request.getSession(true);session.setAttribute("researcherId", r);
        if (r != 0) {
            return Response.ok().entity(r).build();
        } else {
            return Response.notModified().build();
        }
    }

    // load researcher account detail according to the logged in person
    @GET
    @Path("/getResearcher")
    @Produces(MediaType.APPLICATION_JSON)
    public ResearcherDTO loadResearcher(@Context HttpServletRequest request) throws SQLException {
        HttpSession httpSession = request.getSession(true);
        Object userId = httpSession.getAttribute("researcherId");
        if(userId != null){
            System.out.println(userId.toString());
        }
        else {
            httpSession.setAttribute("researcherId", "");
        }
        String researcherId = (httpSession.getAttribute("researcherId").toString());
        System.out.println("sssssssssssssss"+researcherId);
        return researcherModel.findResearcher(researcherId);
    }

    //update researcher account
    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String updateResearcher(ResearcherDTO researcherDTO) throws Exception {
        String output = researcherObject.updateResearcherDetail(
                researcherDTO.getfName(),
                researcherDTO.getlName(),
                researcherDTO.getEmail(),
                researcherDTO.getContactNo(),
                researcherDTO.getAddress(),
                researcherDTO.getZipCode(),
                researcherDTO.getRate()
        );
        return output;
    }

    //delete researcher account
    @DELETE
    @Path("/delete/{id}")
    public Response deleteResearcher(@PathParam("id") int id){
        if(researcherModel.deleteResearcher(id)){
            return Response.ok().build();
        }
        else{
            return Response.notModified().build();
        }
    }
}
