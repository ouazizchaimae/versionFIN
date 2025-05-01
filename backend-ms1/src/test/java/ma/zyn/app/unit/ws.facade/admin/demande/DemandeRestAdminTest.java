package ma.zyn.app.unit.ws.facade.admin.demande;

import ma.zyn.app.bean.core.demande.Demande;
import ma.zyn.app.service.impl.admin.demande.DemandeAdminServiceImpl;
import ma.zyn.app.ws.facade.admin.demande.DemandeRestAdmin;
import ma.zyn.app.ws.converter.demande.DemandeConverter;
import ma.zyn.app.ws.dto.demande.DemandeDto;
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
public class DemandeRestAdminTest {

    private MockMvc mockMvc;

    @Mock
    private DemandeAdminServiceImpl service;
    @Mock
    private DemandeConverter converter;

    @InjectMocks
    private DemandeRestAdmin controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllDemandeTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<DemandeDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<DemandeDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSaveDemandeTest() throws Exception {
        // Mock data
        DemandeDto requestDto = new DemandeDto();
        Demande entity = new Demande();
        Demande saved = new Demande();
        DemandeDto savedDto = new DemandeDto();

        // Mock the converter to return the demande object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved demande DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<DemandeDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        DemandeDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved demande DTO
        assertEquals(savedDto.getCode(), responseBody.getCode());
        assertEquals(savedDto.getLibelle(), responseBody.getLibelle());
        assertEquals(savedDto.getDescription(), responseBody.getDescription());
        assertEquals(savedDto.getDateDemande(), responseBody.getDateDemande());
        assertEquals(savedDto.getDateExpedition(), responseBody.getDateExpedition());
        assertEquals(savedDto.getVolume(), responseBody.getVolume());
        assertEquals(savedDto.getActionEntreprise(), responseBody.getActionEntreprise());
        assertEquals(savedDto.getTrg(), responseBody.getTrg());
        assertEquals(savedDto.getCause(), responseBody.getCause());
        assertEquals(savedDto.getCommentaire(), responseBody.getCommentaire());
    }

}
