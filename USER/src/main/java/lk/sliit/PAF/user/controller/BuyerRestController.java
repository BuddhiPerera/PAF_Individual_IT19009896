package lk.sliit.PAF.user.controller;

import lk.sliit.PAF.user.dto.BuyerDTO;
import lk.sliit.PAF.user.model.BuyerModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Path("/buyer")
public class BuyerRestController {

    BuyerModel buyerModel = BuyerModel.getInstance();

    BuyerModel buyerObject = new BuyerModel();

    //save a buyer
    @POST
    @Path("/saveUser")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response addBuyer(BuyerDTO buyerDTO, @Context HttpServletRequest request) throws Exception {
        int s =buyerObject.insertBuyerDetail(
                buyerDTO.getfName(),
                buyerDTO.getlName(),
                buyerDTO.getEmail(),
                buyerDTO.getContactNo(),
                buyerDTO.getAddress(),
                buyerDTO.getZipCode(),
                buyerDTO.getPassword()
        );

        // save the buyer id in the session
        HttpSession session= request.getSession(true);session.setAttribute("buyerId", s);
        if (s != 0) {
            return Response.ok().entity(s).build();
        } else {
            return Response.notModified().build();
        }
    }

    // load buyer account detail according to the logged in person
    @GET
    @Path("/getBuyer")
    @Produces(MediaType.APPLICATION_JSON)
    public BuyerDTO loadBuyer(@Context HttpServletRequest request) throws SQLException {
        HttpSession httpSession = request.getSession(true);
        Object userId = httpSession.getAttribute("buyerId");
        if(userId != null){
            System.out.println(userId.toString());
        }
        else {
            httpSession.setAttribute("buyerId", "");
        }
        String buyerId = (httpSession.getAttribute("buyerId").toString());
        return buyerModel.findBuyer(buyerId);
    }

    //update buyer account
    @PUT
    @Path("/updateBuyer")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String updateBuyer(BuyerDTO buyerDTO){
        System.out.println(buyerDTO + "ssssssssssssssssssssssssssss");
        buyerObject.updateBuyerDetail(
                buyerDTO.getfName(),
                buyerDTO.getlName(),
                buyerDTO.getEmail(),
                buyerDTO.getContactNo(),
                buyerDTO.getAddress(),
                buyerDTO.getZipCode(),
                buyerDTO.getPassword()
        );
        return "Update Success";
    }

    //delete buyer account
    @DELETE
    @Path("/delete/{id}")
    public Response deleteBuyer(@PathParam("id") int id){
        if(buyerModel.deleteBuyer(id)){
            return Response.ok().build();
        }
        else{
            return Response.notModified().build();
        }
    }
}
