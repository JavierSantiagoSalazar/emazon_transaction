package com.pragma.emazon_transaction.infrastructure.input.rest;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.pragma.emazon_transaction.application.dto.supply.ListSupplyRequest;
import com.pragma.emazon_transaction.application.dto.supply.SupplyRequest;
import com.pragma.emazon_transaction.application.handler.supply.SupplyHandler;
import com.pragma.emazon_transaction.domain.exceptions.ArticleRestockDateNotFoundException;
import com.pragma.emazon_transaction.domain.utils.Constants;
import com.pragma.emazon_transaction.infrastructure.configuration.security.filter.JwtValidatorFilter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = SupplyRestController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class SupplyRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SupplyHandler supplyHandler;

    @MockBean
    private JwtValidatorFilter jwtValidatorFilter;

    @Autowired
    private ObjectMapper objectMapper;

    private ListSupplyRequest validListSupplyRequest;
    private ListSupplyRequest invalidListSupplyRequest;

    @BeforeEach
    public void setUp() {

        validListSupplyRequest = new ListSupplyRequest();
        validListSupplyRequest.setData(List.of(
                new SupplyRequest(1, 100),
                new SupplyRequest(2, 200)
        ));

        invalidListSupplyRequest = new ListSupplyRequest();
        invalidListSupplyRequest.setData(List.of(
                new SupplyRequest(null, 100),
                new SupplyRequest(2, -50)
        ));
    }

    @Test
    void givenValidRequest_whenAddSupplyToStock_thenReturns200() throws Exception {

        doNothing().when(supplyHandler).addSupplyToStock(validListSupplyRequest.getData());

        mockMvc.perform(post("/supply/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(validListSupplyRequest)))
                .andExpect(status().isOk());
    }

    @Test
    void givenInvalidRequest_whenAddSupplyToStock_thenReturns400() throws Exception {

        mockMvc.perform(post("/supply/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidListSupplyRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.statusCode").value(HttpStatus.BAD_REQUEST.name()))
                .andExpect(jsonPath("$.message").value(containsString("Article amount must be a positive number")))
                .andExpect(jsonPath("$.message").value(containsString("Article ID cannot be null")));
    }

    @Test
    void givenValidSupplyRequest_whenAddNewRegisterFromStock_thenReturns200() throws Exception {

        SupplyRequest validSupplyRequest = new SupplyRequest(1, 100);

        doNothing().when(supplyHandler).addNewRegisterFromStock(validSupplyRequest);

        mockMvc.perform(post("/supply/add-new-register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(validSupplyRequest)))
                .andExpect(status().isOk());
    }

    @Test
    void givenInvalidSupplyRequest_whenAddNewRegisterFromStock_thenReturns400() throws Exception {

        SupplyRequest invalidSupplyRequest = new SupplyRequest(null, -50); // Datos inválidos

        mockMvc.perform(post("/supply/add-new-register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidSupplyRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.statusCode").value(HttpStatus.BAD_REQUEST.name()))
                .andExpect(jsonPath("$.message").value(containsString("Article ID cannot be null")))
                .andExpect(jsonPath("$.message").value(containsString("Article amount must be a positive number")));
    }

    @Test
    void givenValidArticleIds_whenGetRestockDate_thenReturns200AndRestockDates() throws Exception {

        List<Integer> articleIds = List.of(1, 2, 3);
        List<LocalDate> restockDates = List.of(
                LocalDate.now(),
                LocalDate.now().plusDays(1),
                LocalDate.now().plusDays(2)
        );

        when(supplyHandler.getRestockDate(articleIds)).thenReturn(restockDates);

        mockMvc.perform(get("/supply/")
                        .param("articleIdList", "1", "2", "3")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3))
                .andExpect(jsonPath("$[0]").value(restockDates.get(0).toString()))
                .andExpect(jsonPath("$[1]").value(restockDates.get(1).toString()))
                .andExpect(jsonPath("$[2]").value(restockDates.get(2).toString()));
    }

    @Test
    void givenMismatchedArticleIds_whenGetRestockDate_thenThrowsArticleRestockDateNotFoundException() throws Exception {

        List<Integer> articleIds = List.of(1, 2, 3);

        when(supplyHandler.getRestockDate(articleIds))
                .thenThrow(new ArticleRestockDateNotFoundException(Constants.SUPPLY_NO_RESTOCKING_DATE));

        mockMvc.perform(get("/supply/")
                        .param("articleIdList", "1", "2", "3")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.statusCode").value(HttpStatus.NOT_FOUND.name()))
                .andExpect(jsonPath("$.message").value(Constants.SUPPLY_NO_RESTOCKING_DATE));
    }

}
