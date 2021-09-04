package com.example.layerd_architecture.controller;

import com.example.layerd_architecture.config.ApplicationConfig;
import com.example.layerd_architecture.config.WebMvcContextConfiguration;
import com.example.layerd_architecture.dto.Guestbook;
import com.example.layerd_architecture.service.GuestbookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebMvcContextConfiguration.class, ApplicationConfig.class })
public class GuestbookApiControllerTest {
    @InjectMocks
    public GuestbookApiController guestbookApiController;

    @Mock
    GuestbookService guestbookService;

    private MockMvc mockMvc;

    @BeforeEach
    public void createController() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(guestbookApiController).build();
    }

    @Test
    public void getGuestbooks() throws Exception {
        Guestbook guestbook1 = new Guestbook();
        guestbook1.setId(1L);
        guestbook1.setRegdate(new Date());
        guestbook1.setContent("hello");
        guestbook1.setName("kim");

        List<Guestbook> list = Arrays.asList(guestbook1);
        when(guestbookService.getGuestbooks(0)).thenReturn(list);

        RequestBuilder reqBuilder = MockMvcRequestBuilders.get("/guestbooks").contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(reqBuilder).andExpect(status().isOk()).andDo(print());

        verify(guestbookService).getGuestbooks(0);
    }

    @Test
    public void deleteGuestbook() throws Exception {
        Long id = 1L;

        when(guestbookService.deleteGuestbook(id, "127.0.0.1")).thenReturn(1);

        RequestBuilder reqBuilder = MockMvcRequestBuilders.delete("/guestbooks/" + id).contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(reqBuilder).andExpect(status().isOk()).andDo(print());

        verify(guestbookService).deleteGuestbook(id, "127.0.0.1");
    }
}
