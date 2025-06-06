package ma.zyn.app.unit.service.impl.admin.expedition;

import ma.zyn.app.bean.core.expedition.AnalyseChimiqueDetail;
import ma.zyn.app.dao.facade.core.expedition.AnalyseChimiqueDetailDao;
import ma.zyn.app.service.impl.admin.expedition.AnalyseChimiqueDetailAdminServiceImpl;

import ma.zyn.app.bean.core.referentiel.ElementChimique ;
import ma.zyn.app.bean.core.expedition.AnalyseChimique ;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDateTime;



import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringBootTest
class AnalyseChimiqueDetailAdminServiceImplTest {

    @Mock
    private AnalyseChimiqueDetailDao repository;
    private AutoCloseable autoCloseable;
    private AnalyseChimiqueDetailAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new AnalyseChimiqueDetailAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllAnalyseChimiqueDetail() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveAnalyseChimiqueDetail() {
        // Given
        AnalyseChimiqueDetail toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteAnalyseChimiqueDetail() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetAnalyseChimiqueDetailById() {
        // Given
        Long idToRetrieve = 1L; // Example AnalyseChimiqueDetail ID to retrieve
        AnalyseChimiqueDetail expected = new AnalyseChimiqueDetail(); // You need to replace AnalyseChimiqueDetail with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        AnalyseChimiqueDetail result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private AnalyseChimiqueDetail constructSample(int i) {
		AnalyseChimiqueDetail given = new AnalyseChimiqueDetail();
        given.setLibelle("libelle-"+i);
        given.setDescription("description-"+i);
        given.setElementChimique(new ElementChimique(1L));
        given.setValeur(BigDecimal.TEN);
        given.setConformite(false);
        given.setSurqualite(false);
        given.setAnalyseChimique(new AnalyseChimique(1L));
        return given;
    }

}
