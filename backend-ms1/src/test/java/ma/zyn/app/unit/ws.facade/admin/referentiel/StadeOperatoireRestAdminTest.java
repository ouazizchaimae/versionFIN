package ma.zyn.app.unit.ws.facade.admin.referentiel;

import ma.zyn.app.bean.core.referentiel.StadeOperatoire;
import ma.zyn.app.service.impl.admin.referentiel.StadeOperatoireAdminServiceImpl;
import ma.zyn.app.ws.facade.admin.referentiel.StadeOperatoireRestAdmin;
import ma.zyn.app.ws.converter.referentiel.StadeOperatoireConverter;
import ma.zyn.app.ws.dto.referentiel.StadeOperatoireDto;
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
public class StadeOperatoireRestAdminTest {

    private MockMvc mockMvc;

    @Mock
    private StadeOperatoireAdminServiceImpl service;
    @Mock
    private StadeOperatoireConverter converter;

    @InjectMocks
    private StadeOperatoireRestAdmin controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllStadeOperatoireTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<StadeOperatoireDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<StadeOperatoireDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSaveStadeOperatoireTest() throws Exception {
        // Mock data
        StadeOperatoireDto requestDto = new StadeOperatoireDto();
        StadeOperatoire entity = new StadeOperatoire();
        StadeOperatoire saved = new StadeOperatoire();
        StadeOperatoireDto savedDto = new StadeOperatoireDto();

        // Mock the converter to return the stadeOperatoire object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved stadeOperatoire DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<StadeOperatoireDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        StadeOperatoireDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved stadeOperatoire DTO
        assertEquals(savedDto.getCode(), responseBody.getCode());
        assertEquals(savedDto.getLibelle(), responseBody.getLibelle());
        assertEquals(savedDto.getStyle(), responseBody.getStyle());
        assertEquals(savedDto.getDescription(), responseBody.getDescription());
        assertEquals(savedDto.getCapaciteMin(), responseBody.getCapaciteMin());
        assertEquals(savedDto.getCapaciteMax(), responseBody.getCapaciteMax());
        assertEquals(savedDto.getIndice(), responseBody.getIndice());
    }

}
