package com.example.projetjee.services;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import com.example.projetjee.utils.DataStorage;
import com.example.projetjee.models.User;


@Path("password")
public class ChangePasswordService {
    DataStorage dataStorage = DataStorage.getInstance();
    @POST
    @Path("change")
    @Produces(MediaType.APPLICATION_JSON)
    public Response changePassword(
            @QueryParam("username") String username,
            @QueryParam("old_password") String oldPassword,
            @QueryParam("new_password") String newPassword
    ) {

        User user = dataStorage.getUser(username);

        // Check if the user exists
        if (user == null) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        // Check if the old and new passwords are not empty
        if (
                (newPassword == null || newPassword.isEmpty()) ||
                        (oldPassword == null || oldPassword.isEmpty())
        ) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Please set both old and new password")
                    .build();
        }

        // Check if the old password is correct
        if(!user.getPassword().equals(oldPassword)){
            return Response.status(Response.Status.UNAUTHORIZED).entity("Old password is incorrect").build();
        }

        // Update the password
        dataStorage.changePassword(user.getUsername(), newPassword);
        // Return a success message
        return Response.ok().entity("Password was updated successfully :)").build();

    }
}