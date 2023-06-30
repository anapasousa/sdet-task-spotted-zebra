package com.sdet;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sdet.task.GraphQLRequest;
import com.sdet.utils.JsonUtils;

public class PositiveScenariosTest {
    
    @Test
    public void runPositiveScenarios() throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        System.out.println("Running positive scenarios...");

        // Test 1: Retrieve all roles
        String query1 = "{ \"query\": \"{ Roles { id name } }\" }";
        String response1 = GraphQLRequest.executeQuery(query1);
        
        System.out.println("Retrieving all Roles Test Response:\n" + gson.toJson(gson.fromJson(response1, Object.class)));
        // Assert the response contains the expected information
        Assertions.assertTrue(response1.contains("Roles"));
        // Check if the "Roles" field is empty
        if (response1.contains("\"Roles\": []")) {
            // The "Roles" field is empty
            System.out.println("The 'Roles' field is empty.");
        } else {
            // The "Roles" field is not empty
            System.out.println("The 'Roles' field is not empty.");
            Assertions.assertTrue(response1.contains("id"));
            Assertions.assertTrue(response1.contains("name"));
        }
        
        // Test 2: Create a new role
        String mutation2 = "{ \"query\": \"mutation { RoleCreateOne(name: \\\"New Role\\\") { id name } }\" }";
        String response2 = GraphQLRequest.executeQuery(mutation2);
        System.out.println("Creating a New Role Test Response:\n" + gson.toJson(gson.fromJson(response2, Object.class)));
        
        Assertions.assertTrue(response2.contains("RoleCreateOne"));
        Assertions.assertTrue(response2.contains("id"));
        Assertions.assertTrue(response2.contains("name"));

        //Retrieve all Roles again and assert
        response1 = GraphQLRequest.executeQuery(query1);
        Assertions.assertTrue(response1.contains("id"));
        Assertions.assertTrue(response1.contains("name"));
        System.out.println("Retrieving all Roles Again Response:\n" + gson.toJson(gson.fromJson(response1, Object.class)));


        //saving a jsonObject for usage later
        JsonObject jsonObject2 = JsonUtils.parseJson(response2);
        String id = JsonUtils.getStringValue(jsonObject2, "data", "RoleCreateOne", "id");
        String roleName = JsonUtils.getStringValue(jsonObject2, "data", "RoleCreateOne", "name");

        // Test 8: Update the new role              
        String mutation8 = String.format("{ \"query\": \"mutation { RoleUpdateOne(id: %s, name: \\\"%s\\\") { id name createdAt updatedAt } }\" }", id, roleName);
        String response8 = GraphQLRequest.executeQuery(mutation8);
        System.out.println("Updating a Role Test Response:\n" + gson.toJson(gson.fromJson(response8, Object.class)));
        Assertions.assertTrue(response8.contains("RoleUpdateOne"));
        Assertions.assertTrue(response8.contains("id"));
        Assertions.assertTrue(response8.contains("name"));
        Assertions.assertTrue(response8.contains("updatedAt"));
        Assertions.assertTrue(response8.contains("createdAt"));
        
        // Test 3: Delete the new role
        String mutation3 = String.format("{ \"query\": \"mutation { RoleDeleteOne(id: %s) { affected } }\" }", id);
        String response3 = GraphQLRequest.executeQuery(mutation3);
        System.out.println("Deleting the New Role Test Response:\n" + gson.toJson(gson.fromJson(response3, Object.class)));
        Assertions.assertTrue(response3.contains("RoleDeleteOne"));
        Assertions.assertTrue(response3.contains("affected"));

        //getting the object from affected to be able to assert it impacted something
        JsonObject jsonObject3 = JsonParser.parseString(response3).getAsJsonObject();
        String affected = jsonObject3.get("data").getAsJsonObject().get("RoleDeleteOne")
                                .getAsJsonObject().get("affected").getAsString();
                                System.out.println(affected);
        Assertions.assertTrue(affected.contains("1"));

        // Test 4: Retrieve all skills
        String query4 = "{ \"query\": \"{ Skills { id name } }\" }";
        String response4 = GraphQLRequest.executeQuery(query4);
        System.out.println("Retrieving all Skills Test Response:\n" + gson.toJson(gson.fromJson(response4, Object.class)));
        // Check if the "Skills" field is empty
        if (response4.contains("\"Skills\": []")) {
            // The "Skills" field is empty
            System.out.println("The 'Skills' field is empty.");
        } else {
            // The "Skills" field is not empty
            System.out.println("The 'Skills' field is not empty.");
            Assertions.assertTrue(response4.contains("id"));
            Assertions.assertTrue(response4.contains("name"));
        }

        // Test 5: Create a new skill
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";
        String newSkillName = "New Skill ";
        StringBuilder newSkillNameSB = new StringBuilder(newSkillName.length());
        for (int i = 0; i < 5; i++) {
            int index = (int) (AlphaNumericString.length() * Math.random());
            newSkillNameSB.append(AlphaNumericString.charAt(index));
        }

        String mutation5 = String.format("{ \"query\": \"mutation { SkillCreateOne(name: \\\"%s\\\") { id name } }\" }", newSkillNameSB.toString());
        String response5 = GraphQLRequest.executeQuery(mutation5);
        System.out.println("Creating a New Skill Test Response:\n" + gson.toJson(gson.fromJson(response5, Object.class)));

        JsonObject jsonObject5 = JsonUtils.parseJson(response5);
        String skillId = JsonUtils.getStringValue(jsonObject5, "data", "SkillCreateOne", "id");
        String skillName = JsonUtils.getStringValue(jsonObject5, "data", "SkillCreateOne", "name");

        Assertions.assertNotNull(skillId);
        Assertions.assertNotNull(skillName);

        //Retrieve all Skills again and assert
        response4 = GraphQLRequest.executeQuery(query4);
        Assertions.assertTrue(response4.contains("id"));
        Assertions.assertTrue(response4.contains("name"));
        System.out.println("Retrieving all Skills Again Response:\n" + gson.toJson(gson.fromJson(response4, Object.class)));


        // Test 6: Update a skill
        String mutation6 = String.format("{ \"query\": \"mutation { SkillUpdateOne(id: %s, name: \\\"%s\\\") { id name createdAt updatedAt } }\" }", skillId, skillName);
        String response6 = GraphQLRequest.executeQuery(mutation6);
        System.out.println("Updating a Skill Test Response:\n" + gson.toJson(gson.fromJson(response6, Object.class)));

        Assertions.assertTrue(response6.contains("SkillUpdateOne"));
        Assertions.assertTrue(response6.contains("id"));
        Assertions.assertTrue(response6.contains("name"));
        Assertions.assertTrue(response6.contains("updatedAt"));
        Assertions.assertTrue(response6.contains("createdAt"));

        // Test 7: Delete the new skill
        String mutation7 = String.format("{ \"query\": \"mutation { SkillDeleteOne(id: %s) { affected } }\" }", skillId);
        String response7 = GraphQLRequest.executeQuery(mutation7);
        System.out.println("Deleting the New Skill Test Response:\n" + gson.toJson(gson.fromJson(response7, Object.class)));

        Assertions.assertTrue(response7.contains("SkillDeleteOne"));
        Assertions.assertTrue(response7.contains("affected"));

        //getting the object from affected to be able to assert it impacted something
        JsonObject jsonObject7 = JsonUtils.parseJson(response7);
        String affected7 = JsonUtils.getStringValue(jsonObject7, "data", "SkillDeleteOne", "affected");
        Assertions.assertTrue(affected7.contains("1"));


        // Add more positive test scenarios 
        // include updates to the existing roles/skills and to Overwrite

        System.out.println("Positive scenarios completed.\n\n\n");
    }
}
