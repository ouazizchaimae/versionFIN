package ma.zyn.app.unit.ws.facade.admin.referentiel;

import ma.zyn.app.bean.core.referentiel.RatioUnite;
import ma.zyn.app.service.impl.admin.referentiel.RatioUniteAdminServiceImpl;
import ma.zyn.app.ws.facade.admin.referentiel.RatioUniteRestAdmin;
import ma.zyn.app.ws.converter.referentiel.RatioUniteConverter;
import ma.zyn.app.ws.dto.referentiel.RatioUniteDto;
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
public class RatioUniteRestAdminTest {

    private MockMvc mockMvc;

    @Mock
    private RatioUniteAdminServiceImpl service;
    @Mock
    private RatioUniteConverter converter;

    @InjectMocks
    private RatioUniteRestAdmin controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllRatioUniteTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<RatioUniteDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<RatioUniteDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSaveRatioUniteTest() throws Exception {
        // Mock data
        RatioUniteDto requestDto = new RatioUniteDto();
        RatioUnite entity = new RatioUnite();
        RatioUnite saved = new RatioUnite();
        RatioUniteDto savedDto = new RatioUniteDto();

        // Mock the converter to return the ratioUnite object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved ratioUnite DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<RatioUniteDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        RatioUniteDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved ratioUnite DTO
        assertEquals(savedDto.getRatio(), responseBody.getRatio());
    }

}
