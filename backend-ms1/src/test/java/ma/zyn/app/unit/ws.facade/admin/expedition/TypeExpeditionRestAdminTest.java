package ma.zyn.app.unit.ws.facade.admin.expedition;

import ma.zyn.app.bean.core.expedition.TypeExpedition;
import ma.zyn.app.service.impl.admin.expedition.TypeExpeditionAdminServiceImpl;
import ma.zyn.app.ws.facade.admin.expedition.TypeExpeditionRestAdmin;
import ma.zyn.app.ws.converter.expedition.TypeExpeditionConverter;
import ma.zyn.app.ws.dto.expedition.TypeExpeditionDto;
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
public class TypeExpeditionRestAdminTest {

    private MockMvc mockMvc;

    @Mock
    private TypeExpeditionAdminServiceImpl service;
    @Mock
    private TypeExpeditionConverter converter;

    @InjectMocks
    private TypeExpeditionRestAdmin controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllTypeExpeditionTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<TypeExpeditionDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<TypeExpeditionDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSaveTypeExpeditionTest() throws Exception {
        // Mock data
        TypeExpeditionDto requestDto = new TypeExpeditionDto();
        TypeExpedition entity = new TypeExpedition();
        TypeExpedition saved = new TypeExpedition();
        TypeExpeditionDto savedDto = new TypeExpeditionDto();

        // Mock the converter to return the typeExpedition object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved typeExpedition DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<TypeExpeditionDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        TypeExpeditionDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved typeExpedition DTO
        assertEquals(savedDto.getLibelle(), responseBody.getLibelle());
        assertEquals(savedDto.getCode(), responseBody.getCode());
        assertEquals(savedDto.getStyle(), responseBody.getStyle());
        assertEquals(savedDto.getDescription(), responseBody.getDescription());
    }

}
