package ma.zyn.app.unit.ws.facade.admin.supply;

import ma.zyn.app.bean.core.supply.SuiviProduction;
import ma.zyn.app.service.impl.admin.supply.SuiviProductionAdminServiceImpl;
import ma.zyn.app.ws.facade.admin.supply.SuiviProductionRestAdmin;
import ma.zyn.app.ws.converter.supply.SuiviProductionConverter;
import ma.zyn.app.ws.dto.supply.SuiviProductionDto;
import org.aspectj.lang.annotation.Before;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SuiviProductionRestAdminTest {

    private MockMvc mockMvc;

    @Mock
    private SuiviProductionAdminServiceImpl service;
    @Mock
    private SuiviProductionConverter converter;

    @InjectMocks
    private SuiviProductionRestAdmin controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllSuiviProductionTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<SuiviProductionDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<SuiviProductionDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSaveSuiviProductionTest() throws Exception {
        // Mock data
        SuiviProductionDto requestDto = new SuiviProductionDto();
        SuiviProduction entity = new SuiviProduction();
        SuiviProduction saved = new SuiviProduction();
        SuiviProductionDto savedDto = new SuiviProductionDto();

        // Mock the converter to return the suiviProduction object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved suiviProduction DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<SuiviProductionDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        SuiviProductionDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved suiviProduction DTO
        assertEquals(savedDto.getCode(), responseBody.getCode());
        assertEquals(savedDto.getLibelle(), responseBody.getLibelle());
        assertEquals(savedDto.getDescription(), responseBody.getDescription());
        assertEquals(savedDto.getJour(), responseBody.getJour());
        assertEquals(savedDto.getVolume(), responseBody.getVolume());
        assertEquals(savedDto.getTsm(), responseBody.getTsm());
    }

}
