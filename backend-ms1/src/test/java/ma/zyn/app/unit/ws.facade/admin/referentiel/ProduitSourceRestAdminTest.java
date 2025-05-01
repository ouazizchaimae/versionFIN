package ma.zyn.app.unit.ws.facade.admin.referentiel;

import ma.zyn.app.bean.core.referentiel.ProduitSource;
import ma.zyn.app.service.impl.admin.referentiel.ProduitSourceAdminServiceImpl;
import ma.zyn.app.ws.facade.admin.referentiel.ProduitSourceRestAdmin;
import ma.zyn.app.ws.converter.referentiel.ProduitSourceConverter;
import ma.zyn.app.ws.dto.referentiel.ProduitSourceDto;
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
public class ProduitSourceRestAdminTest {

    private MockMvc mockMvc;

    @Mock
    private ProduitSourceAdminServiceImpl service;
    @Mock
    private ProduitSourceConverter converter;

    @InjectMocks
    private ProduitSourceRestAdmin controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllProduitSourceTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<ProduitSourceDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<ProduitSourceDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSaveProduitSourceTest() throws Exception {
        // Mock data
        ProduitSourceDto requestDto = new ProduitSourceDto();
        ProduitSource entity = new ProduitSource();
        ProduitSource saved = new ProduitSource();
        ProduitSourceDto savedDto = new ProduitSourceDto();

        // Mock the converter to return the produitSource object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved produitSource DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<ProduitSourceDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        ProduitSourceDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved produitSource DTO
        assertEquals(savedDto.getCode(), responseBody.getCode());
        assertEquals(savedDto.getLibelle(), responseBody.getLibelle());
        assertEquals(savedDto.getStyle(), responseBody.getStyle());
        assertEquals(savedDto.getDescription(), responseBody.getDescription());
    }

}
