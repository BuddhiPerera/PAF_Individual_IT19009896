package lk.sliit.PAF.user.controller;

import lk.sliit.PAF.user.dto.AdminLoginDTO;
import lk.sliit.PAF.user.dto.BuyerDTO;
import lk.sliit.PAF.user.model.AdminModel;
import lk.sliit.PAF.user.model.BuyerModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/Login")
public class LoginRestController {
//    BuyerModel buyerModel = BuyerModel.getInstance();
//    @GET
//    @Path("/profile")
//    @Produces(MediaType.APPLICATION_JSON)
//    public List<BuyerDTO> buyerDTOList (@Context HttpServletRequest request) throws Exception {
//        HttpSession httpSession = request.getSession(true);
//        Object userId = httpSession.getAttribute("userId");
//        if(userId == null){
//            System.out.println(userId.toString());
//        }
//        else {
//            httpSession.setAttribute("userId", "");
//        }
//
//        String customerId = (httpSession.getAttribute("userId").toString());
//        System.out.println(customerId+" sssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss");
//        return buyerModel.listAllBuyers();
//    }

    @GET
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public void list2( @Context HttpServletRequest req) throws Exception {
        System.out.println("sssssssssssssssssssssssaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        HttpSession session= req.getSession(true);
        session.setAttribute("userId", "1");
    }

}
