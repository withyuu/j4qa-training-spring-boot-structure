package com.ascend.training.j4qa.structure.controllers;

import com.ascend.training.j4qa.structure.configs.ApplicationConfiguration;
import com.ascend.training.j4qa.structure.entities.Toy;
import com.ascend.training.j4qa.structure.services.ToyService;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ToyController.class)
public class ToyControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ToyService toyService;

    @MockBean
    private ApplicationConfiguration config;

    @Test
    public void givenToys_whenGetToys_thenReturnJsonArray() throws Exception {

        Toy toy1 = new Toy("LEGO", "60157", "Jungle Starter Set");
        toy1.setId(1L);
        Toy toy2 = new Toy("LEGO", "'75531'", "Stormtrooper Commander");
        toy2.setId(2L);

        List<Toy> allToys = Arrays.asList(toy1, toy2);

        given(toyService.getToyList()).willReturn(allToys);

        mvc.perform(get("/toys")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is("SUCCESS")))
                .andExpect(jsonPath("$.data", hasSize(2)))
                .andExpect(jsonPath("$.data[0].id").value(Matchers.anyOf(
                        Matchers.equalTo((Number) toy1.getId()),
                        Matchers.equalTo((Number) toy1.getId().intValue()))))
                .andExpect(jsonPath("$.data[0].brand", is(toy1.getBrand())))
                .andExpect(jsonPath("$.data[0].model", is(toy1.getModel())))
                .andExpect(jsonPath("$.data[0].name",  is(toy1.getName())))
                .andExpect(jsonPath("$.data[1].id").value(Matchers.anyOf(
                        Matchers.equalTo((Number) toy2.getId()),
                        Matchers.equalTo((Number) toy2.getId().intValue()))))
                .andExpect(jsonPath("$.data[1].brand", is(toy2.getBrand())))
                .andExpect(jsonPath("$.data[1].model", is(toy2.getModel())))
                .andExpect(jsonPath("$.data[1].name",  is(toy2.getName())));
    }

}
