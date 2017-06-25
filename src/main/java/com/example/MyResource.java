package com.example;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.UUID;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("/myresource")
public class MyResource {
    private Connection connection;
    private Statement statement;
    private String output;
    private String query;

    @GET
    @Path("/list")
    @Produces(MediaType.TEXT_PLAIN)
    public String getMsg() {
        try {
            dbConnect();
            output = getPersonsAndCreateGetOutput();
            dbClose();
        } catch (Exception e){
            output = e.toString();
        }
        return output;
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response createMessage(@FormParam("name") String name, @FormParam("adress") String adress, @FormParam("phone") String phone){
        int result;

        try {
            dbConnect();
            result = dbInsert(name.trim(), adress.trim(), phone.trim());
            output = createPostOutput(result);
            dbClose();
        } catch(Exception e){
            output = e.toString();
        }

        return Response.created(URI.create("/webapi/myresource/" + String.valueOf(UUID.randomUUID()))).entity(output).build();
    }

    private int dbInsert(String name, String adress, String phone) throws Exception{
        query = "insert into triona_person values (null, '" + name + "', '" + adress + "', '" + phone + "');";
        return statement.executeUpdate(query);
    }

    private String createPostOutput(int result){
        if (result == 0) {
            return "query wrong";
        } else {
            return "query executed";
        }
    }

    private String getPersonsAndCreateGetOutput() throws Exception{
        ResultSet res = getPersons();
        return createGetOutput(res);
    }

    private ResultSet getPersons() throws Exception {
        query = "select * from triona_person;";
        return statement.executeQuery(query);
    }

    private String createGetOutput(ResultSet res) throws Exception{
        String s;
        s = "Information about the persons:";
        while(res.next()){
            s += "\n\nName: " + res.getString("name") + "\n";
            s += "Adress: " + res.getString("adress") + "\n";
            s += "Phone number: " + res.getString("phone");
        }
        return s;
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

