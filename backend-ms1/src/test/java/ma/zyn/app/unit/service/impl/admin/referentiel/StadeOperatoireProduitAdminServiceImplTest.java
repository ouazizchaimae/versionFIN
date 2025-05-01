package ma.zyn.app.unit.service.impl.admin.referentiel;

import ma.zyn.app.bean.core.referentiel.StadeOperatoireProduit;
import ma.zyn.app.dao.facade.core.referentiel.StadeOperatoireProduitDao;
import ma.zyn.app.service.impl.admin.referentiel.StadeOperatoireProduitAdminServiceImpl;

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
class StadeOperatoireProduitAdminServiceImplTest {

    @Mock
    private StadeOperatoireProduitDao repository;
    private AutoCloseable autoCloseable;
    private StadeOperatoireProduitAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new StadeOperatoireProduitAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllStadeOperatoireProduit() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveStadeOperatoireProduit() {
        // Given
        StadeOperatoireProduit toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteStadeOperatoireProduit() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetStadeOperatoireProduitById() {
        // Given
        Long idToRetrieve = 1L; // Example StadeOperatoireProduit ID to retrieve
        StadeOperatoireProduit expected = new StadeOperatoireProduit(); // You need to replace StadeOperatoireProduit with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        StadeOperatoireProduit result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private StadeOperatoireProduit constructSample(int i) {
		StadeOperatoireProduit given = new StadeOperatoireProduit();
        given.setStadeOperatoire(new StadeOperatoire(1L));
        given.setProduit(new Produit(1L));
        return given;
    }

}
