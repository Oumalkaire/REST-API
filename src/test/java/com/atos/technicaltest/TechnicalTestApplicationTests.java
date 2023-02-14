package com.atos.technicaltest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.operation.preprocess.Preprocessors;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.atos.technicaltest.dto.UserDto;

@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@SpringBootTest
@AutoConfigureRestDocs(outputDir = "target/generated-snippets")
@TestMethodOrder(OrderAnnotation.class)
class TechnicalTestApplicationTests {

	@Autowired
	WebApplicationContext webApplicationContext;
	@Autowired
    private Jackson2ObjectMapperBuilder objectMapperBuilder;
	
	MockMvc mockMvc;
	UserDto userDto;
	
	@BeforeEach
	void setUp(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentation) {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
				.apply(MockMvcRestDocumentation.documentationConfiguration(restDocumentation)).build();
		
		userDto = new UserDto("oumalkaire", LocalDate.parse("16/10/1994",
				DateTimeFormatter.ofPattern("dd/MM/yyyy")), "France", "0761574451", "f");
	}
	
	@Test
	@Order(1)
	public void testCreateUser() throws Exception{
		
		 this.mockMvc.perform(RestDocumentationRequestBuilders.post("/users")
				 .content(objectMapperBuilder.build().writeValueAsString(userDto))
				 .contentType("application/json")).andDo(MockMvcResultHandlers.print())
		 		 .andExpect(MockMvcResultMatchers.status().isCreated())
		 		 .andDo(MockMvcRestDocumentation.document("{methodName}",
			 				Preprocessors.preprocessRequest(Preprocessors.prettyPrint()), 
			 				Preprocessors.preprocessResponse(Preprocessors.prettyPrint())));
	}
	
	@Test
	@Order(2)
	public void testGetUser() throws Exception {
		 mockMvc.perform(RestDocumentationRequestBuilders.get("/users/{username}",userDto.userName())
				 .contentType("application/json"))
				 .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
				 .andExpect(MockMvcResultMatchers.content().json(objectMapperBuilder.build().writeValueAsString(userDto)))
				 .andDo(MockMvcRestDocumentation.document("{methodName}",
			 				Preprocessors.preprocessRequest(Preprocessors.prettyPrint()), 
			 				Preprocessors.preprocessResponse(Preprocessors.prettyPrint())));
	}
	 

}
