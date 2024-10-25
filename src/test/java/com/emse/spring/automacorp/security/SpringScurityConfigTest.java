package com.emse.spring.automacorp.security;

import com.emse.spring.automacorp.dao.WindowDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class SpringScurityConfigTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WindowDao windowDao;

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void shouldLoadAWindowAndReturnNullIfNotFound() throws Exception {
        given(windowDao.findById(999L)).willReturn(Optional.empty());
        mockMvc.perform(MockMvcRequestBuilders.get("/api/windows/999").accept(APPLICATION_JSON))
                // check the HTTP response
                .andExpect(status().isOk())
                // the content can be tested with Json path
                .andExpect(content().string(""));
    }
}
