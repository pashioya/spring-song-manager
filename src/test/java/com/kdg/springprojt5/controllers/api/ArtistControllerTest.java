package com.kdg.springprojt5.controllers.api;

import com.kdg.springprojt5.repository.ArtistRepository;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ArtistControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ArtistRepository artistRepository;

}
