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
    private Connection connection;
    private Statement statement;

    @GET
    @Path("/list")
    public Response getMsg() {
        String output = "Get:Jersey say :";
        return Response.status(200).entity(output).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response createMessage(@FormParam("name") String name, @FormParam("adress") String adress, @FormParam("phone") String phone){
        int result;
        String output;

        try {
            dbConnect();
            result = dbInsert(name.trim(), adress.trim(), phone.trim());
            output = postOutput(result);
            dbClose();
        } catch(Exception e){
            output = e.toString();
        }

        return Response.created(URI.create("/webapi/myresource/" + String.valueOf(UUID.randomUUID()))).entity(output).build();
    }

    private int dbInsert(String name, String adress, String phone) throws Exception{
        String query = "insert into triona_person values (null, '" + name + "', '" + adress + "', " + phone + ");";
        return statement.executeUpdate(query);
    }

    private String postOutput(int result){
        if (result == 0) {
            return "query wrong";
        } else {
            return "query executed";
        }
    }

    private void dbConnect() throws Exception{
        String username = "espenkb";
        String password = "nN3MZOCh";
        String databasenavn = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/" + username + "?user=" + username + "&password=" + password;
        String databasedriver = "com.mysql.jdbc.Driver";

        Class.forName(databasedriver);

        connection = DriverManager.getConnection(databasenavn);
        statement = connection.createStatement();
    }

    private void dbClose() throws Exception{
        statement.close();
        connection.close();
    }
}

