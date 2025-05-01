package ma.zyn.app.unit.ws.facade.admin.expedition;

import ma.zyn.app.bean.core.expedition.CharteChimique;
import ma.zyn.app.service.impl.admin.expedition.CharteChimiqueAdminServiceImpl;
import ma.zyn.app.ws.facade.admin.expedition.CharteChimiqueRestAdmin;
import ma.zyn.app.ws.converter.expedition.CharteChimiqueConverter;
import ma.zyn.app.ws.dto.expedition.CharteChimiqueDto;
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
public class CharteChimiqueRestAdminTest {

    private MockMvc mockMvc;

    @Mock
    private CharteChimiqueAdminServiceImpl service;
    @Mock
    private CharteChimiqueConverter converter;

    @InjectMocks
    private CharteChimiqueRestAdmin controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllCharteChimiqueTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<CharteChimiqueDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<CharteChimiqueDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSaveCharteChimiqueTest() throws Exception {
        // Mock data
        CharteChimiqueDto requestDto = new CharteChimiqueDto();
        CharteChimique entity = new CharteChimique();
        CharteChimique saved = new CharteChimique();
        CharteChimiqueDto savedDto = new CharteChimiqueDto();

        // Mock the converter to return the charteChimique object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved charteChimique DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<CharteChimiqueDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        CharteChimiqueDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved charteChimique DTO
        assertEquals(savedDto.getCode(), responseBody.getCode());
        assertEquals(savedDto.getLibelle(), responseBody.getLibelle());
        assertEquals(savedDto.getDescription(), responseBody.getDescription());
    }

}
