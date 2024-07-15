package com.socialmedia.socialmedia2024v1.config;

import java.util.Set;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;

public class JsonSchemaCofig {

	private static final String SCHEMA_VALIDATION_FILE = "validation.json";

	public String jsonSchema(Object obj) throws JsonMappingException, JsonProcessingException {
		
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(obj);
		ObjectMapper om = new ObjectMapper();
		
		JsonNode jsonNode;
		
		jsonNode = om.readTree(json);
		
		Set<ValidationMessage> errors = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V7)
				.getSchema(getClass().getResourceAsStream(SCHEMA_VALIDATION_FILE)).validate(jsonNode);
		//if errors have a single miss match, there would be a value in the errors set.
	    if(errors.isEmpty()){
	      //event is valid.
	    	System.out.println("event is valid");
	    }else{
	        //event is in_valid.
	    	System.out.println("event is invalid");
	     }
	      return errors.toString();
	}
	
	

}
