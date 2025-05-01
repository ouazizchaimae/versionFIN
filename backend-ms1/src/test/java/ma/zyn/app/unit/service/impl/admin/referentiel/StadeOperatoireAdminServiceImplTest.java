package ma.zyn.app.unit.service.impl.admin.referentiel;

import ma.zyn.app.bean.core.referentiel.StadeOperatoire;
import ma.zyn.app.dao.facade.core.referentiel.StadeOperatoireDao;
import ma.zyn.app.service.impl.admin.referentiel.StadeOperatoireAdminServiceImpl;

import ma.zyn.app.bean.core.referentiel.StadeOperatoire ;
import ma.zyn.app.bean.core.referentiel.StadeOperatoireProduit ;
import ma.zyn.app.bean.core.referentiel.Entite ;
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
class StadeOperatoireAdminServiceImplTest {

    @Mock
    private StadeOperatoireDao repository;
    private AutoCloseable autoCloseable;
    private StadeOperatoireAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new StadeOperatoireAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllStadeOperatoire() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveStadeOperatoire() {
        // Given
        StadeOperatoire toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteStadeOperatoire() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetStadeOperatoireById() {
        // Given
        Long idToRetrieve = 1L; // Example StadeOperatoire ID to retrieve
        StadeOperatoire expected = new StadeOperatoire(); // You need to replace StadeOperatoire with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        StadeOperatoire result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private StadeOperatoire constructSample(int i) {
		StadeOperatoire given = new StadeOperatoire();
        given.setCode("code-"+i);
        given.setLibelle("libelle-"+i);
        given.setStyle("style-"+i);
        given.setDescription("description-"+i);
        given.setCapaciteMin(BigDecimal.TEN);
        given.setCapaciteMax(BigDecimal.TEN);
        given.setIndice(i);
        given.setEntite(new Entite(1L));
        List<StadeOperatoireProduit> stadeOperatoireProduits = IntStream.rangeClosed(1, 3)
                                             .mapToObj(id -> {
                                                StadeOperatoireProduit element = new StadeOperatoireProduit();
                                                element.setId((long)id);
                                                element.setStadeOperatoire(new StadeOperatoire(Long.valueOf(1)));
                                                element.setProduit(new Produit(Long.valueOf(2)));
                                                return element;
                                             })
                                             .collect(Collectors.toList());
        given.setStadeOperatoireProduits(stadeOperatoireProduits);
        return given;
    }

}
