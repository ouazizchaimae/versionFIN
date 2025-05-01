package ma.zyn.app.unit.ws.facade.admin.expedition;

import ma.zyn.app.bean.core.expedition.CharteChimiqueDetail;
import ma.zyn.app.service.impl.admin.expedition.CharteChimiqueDetailAdminServiceImpl;
import ma.zyn.app.ws.facade.admin.expedition.CharteChimiqueDetailRestAdmin;
import ma.zyn.app.ws.converter.expedition.CharteChimiqueDetailConverter;
import ma.zyn.app.ws.dto.expedition.CharteChimiqueDetailDto;
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
public class CharteChimiqueDetailRestAdminTest {

    private MockMvc mockMvc;

    @Mock
    private CharteChimiqueDetailAdminServiceImpl service;
    @Mock
    private CharteChimiqueDetailConverter converter;

    @InjectMocks
    private CharteChimiqueDetailRestAdmin controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllCharteChimiqueDetailTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<CharteChimiqueDetailDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<CharteChimiqueDetailDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSaveCharteChimiqueDetailTest() throws Exception {
        // Mock data
        CharteChimiqueDetailDto requestDto = new CharteChimiqueDetailDto();
        CharteChimiqueDetail entity = new CharteChimiqueDetail();
        CharteChimiqueDetail saved = new CharteChimiqueDetail();
        CharteChimiqueDetailDto savedDto = new CharteChimiqueDetailDto();

        // Mock the converter to return the charteChimiqueDetail object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved charteChimiqueDetail DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<CharteChimiqueDetailDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        CharteChimiqueDetailDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved charteChimiqueDetail DTO
        assertEquals(savedDto.getLibelle(), responseBody.getLibelle());
        assertEquals(savedDto.getDescription(), responseBody.getDescription());
        assertEquals(savedDto.getMinimum(), responseBody.getMinimum());
        assertEquals(savedDto.getMaximum(), responseBody.getMaximum());
        assertEquals(savedDto.getAverage(), responseBody.getAverage());
        assertEquals(savedDto.getMethodeAnalyse(), responseBody.getMethodeAnalyse());
        assertEquals(savedDto.getUnite(), responseBody.getUnite());
    }

}
