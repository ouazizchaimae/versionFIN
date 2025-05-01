package ma.zyn.app.unit.service.impl.admin.referentiel;

import ma.zyn.app.bean.core.referentiel.RatioUnite;
import ma.zyn.app.dao.facade.core.referentiel.RatioUniteDao;
import ma.zyn.app.service.impl.admin.referentiel.RatioUniteAdminServiceImpl;

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
class RatioUniteAdminServiceImplTest {

    @Mock
    private RatioUniteDao repository;
    private AutoCloseable autoCloseable;
    private RatioUniteAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new RatioUniteAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllRatioUnite() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveRatioUnite() {
        // Given
        RatioUnite toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteRatioUnite() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetRatioUniteById() {
        // Given
        Long idToRetrieve = 1L; // Example RatioUnite ID to retrieve
        RatioUnite expected = new RatioUnite(); // You need to replace RatioUnite with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        RatioUnite result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private RatioUnite constructSample(int i) {
		RatioUnite given = new RatioUnite();
        given.setEntite(new Entite(1L));
        given.setProduit(new Produit(1L));
        given.setRatio(BigDecimal.TEN);
        return given;
    }

}
