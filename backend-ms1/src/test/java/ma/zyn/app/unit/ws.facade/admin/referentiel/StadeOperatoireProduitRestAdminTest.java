package ma.zyn.app.unit.ws.facade.admin.referentiel;

import ma.zyn.app.bean.core.referentiel.StadeOperatoireProduit;
import ma.zyn.app.service.impl.admin.referentiel.StadeOperatoireProduitAdminServiceImpl;
import ma.zyn.app.ws.facade.admin.referentiel.StadeOperatoireProduitRestAdmin;
import ma.zyn.app.ws.converter.referentiel.StadeOperatoireProduitConverter;
import ma.zyn.app.ws.dto.referentiel.StadeOperatoireProduitDto;
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
public class StadeOperatoireProduitRestAdminTest {

    private MockMvc mockMvc;

    @Mock
    private StadeOperatoireProduitAdminServiceImpl service;
    @Mock
    private StadeOperatoireProduitConverter converter;

    @InjectMocks
    private StadeOperatoireProduitRestAdmin controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllStadeOperatoireProduitTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<StadeOperatoireProduitDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<StadeOperatoireProduitDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSaveStadeOperatoireProduitTest() throws Exception {
        // Mock data
        StadeOperatoireProduitDto requestDto = new StadeOperatoireProduitDto();
        StadeOperatoireProduit entity = new StadeOperatoireProduit();
        StadeOperatoireProduit saved = new StadeOperatoireProduit();
        StadeOperatoireProduitDto savedDto = new StadeOperatoireProduitDto();

        // Mock the converter to return the stadeOperatoireProduit object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved stadeOperatoireProduit DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<StadeOperatoireProduitDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        StadeOperatoireProduitDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved stadeOperatoireProduit DTO
    }

}
