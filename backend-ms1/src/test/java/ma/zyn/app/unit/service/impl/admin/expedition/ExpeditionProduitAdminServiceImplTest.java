package ma.zyn.app.unit.service.impl.admin.expedition;

import ma.zyn.app.bean.core.expedition.ExpeditionProduit;
import ma.zyn.app.dao.facade.core.expedition.ExpeditionProduitDao;
import ma.zyn.app.service.impl.admin.expedition.ExpeditionProduitAdminServiceImpl;

import ma.zyn.app.bean.core.expedition.AnalyseChimique ;
import ma.zyn.app.bean.core.expedition.CharteChimique ;
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
class ExpeditionProduitAdminServiceImplTest {

    @Mock
    private ExpeditionProduitDao repository;
    private AutoCloseable autoCloseable;
    private ExpeditionProduitAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new ExpeditionProduitAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllExpeditionProduit() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveExpeditionProduit() {
        // Given
        ExpeditionProduit toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteExpeditionProduit() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetExpeditionProduitById() {
        // Given
        Long idToRetrieve = 1L; // Example ExpeditionProduit ID to retrieve
        ExpeditionProduit expected = new ExpeditionProduit(); // You need to replace ExpeditionProduit with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        ExpeditionProduit result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private ExpeditionProduit constructSample(int i) {
		ExpeditionProduit given = new ExpeditionProduit();
        given.setCode("code-"+i);
        given.setLibelle("libelle-"+i);
        given.setDescription("description-"+i);
        given.setAnalyseChimique(new AnalyseChimique(1L));
        given.setCharteChimique(new CharteChimique(1L));
        return given;
    }

}
