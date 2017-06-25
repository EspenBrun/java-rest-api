package com.example;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.SchemaOutputResolver;
import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.UUID;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("/myresource")
public class MyResource {
    private final String username = "espenkb";
    private final String password = "nN3MZOCh";
    
    @GET
    @Path("/{param}")
    public Response getMsg(@PathParam("param") String msg) {
        String output = "Get:Jersey say : " + msg;
        return Response.status(200).entity(output).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response createMessage(@FormParam("name") String name, @FormParam("adress") String adress, @FormParam("phone") String phone) throws Exception {
        System.out.println("name: " + name + ", adress: " + adress + ", phone: " + phone );
        name = name.trim();
        adress = adress.trim();
        phone = phone.trim();
        String output;

        String databasedriver = "com.mysql.jdbc.Driver";
        Class.forName(databasedriver);

        String databasenavn = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/" + username + "?user=" + username + "&password=" + password;
        Connection connection = DriverManager.getConnection(databasenavn);
        Statement statement = connection.createStatement();

        String query = "insert into triona_person values (null, '" + name + "', '" + adress + "', " + phone + ");";

        int result = statement.executeUpdate(query);

        if (result == 0){
            output = "query wrong";
        } else {
            output = "query executed";
        }

        statement.close();
        connection.close();
        System.out.println(output);

        return Response.created(URI.create("/webapi/myresource/" + String.valueOf(UUID.randomUUID()))).entity(name).build();
    }

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
//    @GET
//    @Produces(MediaType.TEXT_PLAIN)
//    public String getIt() {
//        System.out.print("hello from server");
//        return "Got it!";
//    }
}

