package com.epam.githubinteractor;

import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestUtils
{
    private static final Logger logger = Logger.getLogger(TestUtils.class);

    private static final String JSON_TEST_FILE_NAME = "testfields.json";

    public static ResponseEntity<JsonNode> prepareResponseEntity()
    {
        ObjectMapper mapper = new ObjectMapper();
        InputStream input = GitHubServiceCallManagerTest.class.getClassLoader().getResourceAsStream(JSON_TEST_FILE_NAME);
        JsonNode rootNode = null;
        try {
            rootNode = mapper.readTree(input);
        }
        catch (IOException ex) {
            logger.error("Failed to parse test json file");
        }
        return new ResponseEntity<>(rootNode, HttpStatus.OK);
    }
}
