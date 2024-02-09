package com.example.projetjee.services;

import com.example.projetjee.models.Director;
import com.example.projetjee.models.Role;
import com.example.projetjee.models.User;
import com.example.projetjee.utils.DataStorage;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path("director")
public class CreateDirectorService {

    private DataStorage dataStorage = DataStorage.getInstance();

    @POST
    @Path("create")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createAccount(
            @QueryParam("admin_username") String adminUsername,
             @QueryParam("username") String username,
             @QueryParam("password") String password
    ) {
        var admin = dataStorage.getUser(adminUsername);
        if (admin == null || !admin.getRole().equals(Role.ADMIN)) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
        // create director
        var director = new Director(username, password);
        dataStorage.addUser(director);
        return Response.ok(director).build();
    }
}

