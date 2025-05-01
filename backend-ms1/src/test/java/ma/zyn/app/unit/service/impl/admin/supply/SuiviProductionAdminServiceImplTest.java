package ma.zyn.app.unit.service.impl.admin.supply;

import ma.zyn.app.bean.core.supply.SuiviProduction;
import ma.zyn.app.dao.facade.core.supply.SuiviProductionDao;
import ma.zyn.app.service.impl.admin.supply.SuiviProductionAdminServiceImpl;

import ma.zyn.app.bean.core.referentiel.Unite ;
import ma.zyn.app.bean.core.referentiel.StadeOperatoire ;
import ma.zyn.app.bean.core.referentiel.Produit ;
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
class SuiviProductionAdminServiceImplTest {

    @Mock
    private SuiviProductionDao repository;
    private AutoCloseable autoCloseable;
    private SuiviProductionAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new SuiviProductionAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllSuiviProduction() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveSuiviProduction() {
        // Given
        SuiviProduction toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteSuiviProduction() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetSuiviProductionById() {
        // Given
        Long idToRetrieve = 1L; // Example SuiviProduction ID to retrieve
        SuiviProduction expected = new SuiviProduction(); // You need to replace SuiviProduction with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        SuiviProduction result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private SuiviProduction constructSample(int i) {
		SuiviProduction given = new SuiviProduction();
        given.setCode("code-"+i);
        given.setLibelle("libelle-"+i);
        given.setDescription("description-"+i);
        given.setJour(LocalDateTime.now());
        given.setVolume(BigDecimal.TEN);
        given.setTsm(BigDecimal.TEN);
        given.setProduit(new Produit(1L));
        given.setStadeOperatoire(new StadeOperatoire(1L));
        given.setUnite(new Unite(1L));
        return given;
    }

}
