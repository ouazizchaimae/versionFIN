package ma.zyn.app.unit.service.impl.admin.expedition;

import ma.zyn.app.bean.core.expedition.AnalyseChimique;
import ma.zyn.app.dao.facade.core.expedition.AnalyseChimiqueDao;
import ma.zyn.app.service.impl.admin.expedition.AnalyseChimiqueAdminServiceImpl;

import ma.zyn.app.bean.core.expedition.AnalyseChimiqueDetail ;
import ma.zyn.app.bean.core.referentiel.ElementChimique ;
import ma.zyn.app.bean.core.expedition.AnalyseChimique ;
import ma.zyn.app.bean.core.referentiel.ProduitMarchand ;
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
class AnalyseChimiqueAdminServiceImplTest {

    @Mock
    private AnalyseChimiqueDao repository;
    private AutoCloseable autoCloseable;
    private AnalyseChimiqueAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new AnalyseChimiqueAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllAnalyseChimique() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveAnalyseChimique() {
        // Given
        AnalyseChimique toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteAnalyseChimique() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetAnalyseChimiqueById() {
        // Given
        Long idToRetrieve = 1L; // Example AnalyseChimique ID to retrieve
        AnalyseChimique expected = new AnalyseChimique(); // You need to replace AnalyseChimique with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        AnalyseChimique result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private AnalyseChimique constructSample(int i) {
		AnalyseChimique given = new AnalyseChimique();
        given.setCode("code-"+i);
        given.setLibelle("libelle-"+i);
        given.setDescription("description-"+i);
        given.setProduitMarchand(new ProduitMarchand(1L));
        List<AnalyseChimiqueDetail> analyseChimiqueDetails = IntStream.rangeClosed(1, 3)
                                             .mapToObj(id -> {
                                                AnalyseChimiqueDetail element = new AnalyseChimiqueDetail();
                                                element.setId((long)id);
                                                element.setLibelle("libelle"+id);
                                                element.setDescription("description"+id);
                                                element.setElementChimique(new ElementChimique(Long.valueOf(3)));
                                                element.setValeur(new BigDecimal(4*10));
                                                element.setConformite(true);
                                                element.setSurqualite(true);
                                                element.setAnalyseChimique(new AnalyseChimique(Long.valueOf(7)));
                                                return element;
                                             })
                                             .collect(Collectors.toList());
        given.setAnalyseChimiqueDetails(analyseChimiqueDetails);
        return given;
    }

}
