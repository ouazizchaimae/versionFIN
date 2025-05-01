package ma.zyn.app.unit.service.impl.admin.referentiel;

import ma.zyn.app.bean.core.referentiel.ProduitSource;
import ma.zyn.app.dao.facade.core.referentiel.ProduitSourceDao;
import ma.zyn.app.service.impl.admin.referentiel.ProduitSourceAdminServiceImpl;

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
class ProduitSourceAdminServiceImplTest {

    @Mock
    private ProduitSourceDao repository;
    private AutoCloseable autoCloseable;
    private ProduitSourceAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new ProduitSourceAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllProduitSource() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveProduitSource() {
        // Given
        ProduitSource toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteProduitSource() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetProduitSourceById() {
        // Given
        Long idToRetrieve = 1L; // Example ProduitSource ID to retrieve
        ProduitSource expected = new ProduitSource(); // You need to replace ProduitSource with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        ProduitSource result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private ProduitSource constructSample(int i) {
		ProduitSource given = new ProduitSource();
        given.setCode("code-"+i);
        given.setLibelle("libelle-"+i);
        given.setStyle("style-"+i);
        given.setDescription("description-"+i);
        return given;
    }

}
