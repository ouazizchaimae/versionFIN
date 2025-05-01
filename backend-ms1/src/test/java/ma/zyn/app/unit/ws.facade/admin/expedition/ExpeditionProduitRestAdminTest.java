package ma.zyn.app.unit.ws.facade.admin.expedition;

import ma.zyn.app.bean.core.expedition.ExpeditionProduit;
import ma.zyn.app.service.impl.admin.expedition.ExpeditionProduitAdminServiceImpl;
import ma.zyn.app.ws.facade.admin.expedition.ExpeditionProduitRestAdmin;
import ma.zyn.app.ws.converter.expedition.ExpeditionProduitConverter;
import ma.zyn.app.ws.dto.expedition.ExpeditionProduitDto;
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
public class ExpeditionProduitRestAdminTest {

    private MockMvc mockMvc;

    @Mock
    private ExpeditionProduitAdminServiceImpl service;
    @Mock
    private ExpeditionProduitConverter converter;

    @InjectMocks
    private ExpeditionProduitRestAdmin controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllExpeditionProduitTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<ExpeditionProduitDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<ExpeditionProduitDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSaveExpeditionProduitTest() throws Exception {
        // Mock data
        ExpeditionProduitDto requestDto = new ExpeditionProduitDto();
        ExpeditionProduit entity = new ExpeditionProduit();
        ExpeditionProduit saved = new ExpeditionProduit();
        ExpeditionProduitDto savedDto = new ExpeditionProduitDto();

        // Mock the converter to return the expeditionProduit object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved expeditionProduit DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<ExpeditionProduitDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        ExpeditionProduitDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved expeditionProduit DTO
        assertEquals(savedDto.getCode(), responseBody.getCode());
        assertEquals(savedDto.getLibelle(), responseBody.getLibelle());
        assertEquals(savedDto.getDescription(), responseBody.getDescription());
    }

}
