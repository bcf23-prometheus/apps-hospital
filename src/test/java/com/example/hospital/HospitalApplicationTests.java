package com.example.hospital;

import com.example.hospital.dto.AppointmentDto;
import com.example.hospital.repository.AppointmentRepository;
import com.example.hospital.repository.HospitalRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class HospitalApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private HospitalRepository hospitalRepository;
	@Autowired
	private AppointmentRepository appointmentRepository;
	@Container
	static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:13.2");


	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
		registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
		registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
	}

	@Test
	void shouldCreateProduct() throws Exception {
		AppointmentDto appointmentDto = getAppointmentRequest();
		String productRequestString = objectMapper.writeValueAsString(appointmentDto);
		mockMvc.perform(MockMvcRequestBuilders.post("/api/scheduler/schedule-appointment")
						.contentType(MediaType.APPLICATION_JSON)
						.content(productRequestString))
				.andExpect(status().isCreated());
		Assertions.assertEquals(1, appointmentRepository.findAll().size());
	}

	private AppointmentDto getAppointmentRequest() {
		return AppointmentDto.builder()
				.nid(601325L)
				.hospitalName("Ibn Sina")
				.vaccineName("Pfizer")
				.build();
	}
}
