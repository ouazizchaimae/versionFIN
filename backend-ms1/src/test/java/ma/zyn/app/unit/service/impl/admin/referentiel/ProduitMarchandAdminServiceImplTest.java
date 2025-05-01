package ma.zyn.app.unit.service.impl.admin.referentiel;

import ma.zyn.app.bean.core.referentiel.ProduitMarchand;
import ma.zyn.app.dao.facade.core.referentiel.ProduitMarchandDao;
import ma.zyn.app.service.impl.admin.referentiel.ProduitMarchandAdminServiceImpl;

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
class ProduitMarchandAdminServiceImplTest {

    @Mock
    private ProduitMarchandDao repository;
    private AutoCloseable autoCloseable;
    private ProduitMarchandAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new ProduitMarchandAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllProduitMarchand() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveProduitMarchand() {
        // Given
        ProduitMarchand toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteProduitMarchand() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetProduitMarchandById() {
        // Given
        Long idToRetrieve = 1L; // Example ProduitMarchand ID to retrieve
        ProduitMarchand expected = new ProduitMarchand(); // You need to replace ProduitMarchand with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        ProduitMarchand result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private ProduitMarchand constructSample(int i) {
		ProduitMarchand given = new ProduitMarchand();
        given.setCode("code-"+i);
        given.setLibelle("libelle-"+i);
        given.setStyle("style-"+i);
        given.setDescription("description-"+i);
        return given;
    }

}
