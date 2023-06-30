package com.sdet;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sdet.task.GraphQLRequest;

public class NegativeScenariosTest {
    
    @Test
    public void runNegativeScenarios() throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        
        System.out.println("Running negative scenarios...");

        // Test 1: Retrieve a role with an invalid ID
        String query1 = "{ \"query\": \"{ RoleFindOne(id: -1) { id name } }\" }";
        String response1 = GraphQLRequest.executeQuery(query1);
        System.out.println("Retrieving a role with a Invalid ID Test Response:\n" + gson.toJson(gson.fromJson(response1, Object.class)));

        // Test 2: Retrieve a role with an invalid name
        String query2 = "{ \"query\": \"{ RoleFindOne(name: \"invalid\") { id name } }\" }";
        String response2 = GraphQLRequest.executeQuery(query2);
        System.out.println("Retrieving a role with a Invalid Name Test Response:\n" + gson.toJson(gson.fromJson(response2, Object.class)));


        // Test 3: Attempt to delete a role with an invalid ID
        String mutation3 = "{ \"query\": \"mutation { RoleDeleteOne(id: -1) { affected } }\" }";
        String response3 = GraphQLRequest.executeQuery(mutation3);
        System.out.println("Attempting to Delete a role with an Invalid ID Test Response:\n" + gson.toJson(gson.fromJson(response3, Object.class)));

        // Add more negative test scenarios 

        System.out.println("Negative scenarios completed.");
    }
}
