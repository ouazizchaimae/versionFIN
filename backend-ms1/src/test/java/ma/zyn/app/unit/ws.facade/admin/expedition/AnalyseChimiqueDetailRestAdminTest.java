package ma.zyn.app.unit.ws.facade.admin.expedition;

import ma.zyn.app.bean.core.expedition.AnalyseChimiqueDetail;
import ma.zyn.app.service.impl.admin.expedition.AnalyseChimiqueDetailAdminServiceImpl;
import ma.zyn.app.ws.facade.admin.expedition.AnalyseChimiqueDetailRestAdmin;
import ma.zyn.app.ws.converter.expedition.AnalyseChimiqueDetailConverter;
import ma.zyn.app.ws.dto.expedition.AnalyseChimiqueDetailDto;
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
public class AnalyseChimiqueDetailRestAdminTest {

    private MockMvc mockMvc;

    @Mock
    private AnalyseChimiqueDetailAdminServiceImpl service;
    @Mock
    private AnalyseChimiqueDetailConverter converter;

    @InjectMocks
    private AnalyseChimiqueDetailRestAdmin controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllAnalyseChimiqueDetailTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<AnalyseChimiqueDetailDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<AnalyseChimiqueDetailDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSaveAnalyseChimiqueDetailTest() throws Exception {
        // Mock data
        AnalyseChimiqueDetailDto requestDto = new AnalyseChimiqueDetailDto();
        AnalyseChimiqueDetail entity = new AnalyseChimiqueDetail();
        AnalyseChimiqueDetail saved = new AnalyseChimiqueDetail();
        AnalyseChimiqueDetailDto savedDto = new AnalyseChimiqueDetailDto();

        // Mock the converter to return the analyseChimiqueDetail object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved analyseChimiqueDetail DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<AnalyseChimiqueDetailDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        AnalyseChimiqueDetailDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved analyseChimiqueDetail DTO
        assertEquals(savedDto.getLibelle(), responseBody.getLibelle());
        assertEquals(savedDto.getDescription(), responseBody.getDescription());
        assertEquals(savedDto.getValeur(), responseBody.getValeur());
        assertEquals(savedDto.getConformite(), responseBody.getConformite());
        assertEquals(savedDto.getSurqualite(), responseBody.getSurqualite());
    }

}
