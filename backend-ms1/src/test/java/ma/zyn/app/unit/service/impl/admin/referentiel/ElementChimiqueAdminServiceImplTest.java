package ma.zyn.app.unit.service.impl.admin.referentiel;

import ma.zyn.app.bean.core.referentiel.ElementChimique;
import ma.zyn.app.dao.facade.core.referentiel.ElementChimiqueDao;
import ma.zyn.app.service.impl.admin.referentiel.ElementChimiqueAdminServiceImpl;

import ma.zyn.app.bean.core.referentiel.Unite ;
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
class ElementChimiqueAdminServiceImplTest {

    @Mock
    private ElementChimiqueDao repository;
    private AutoCloseable autoCloseable;
    private ElementChimiqueAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new ElementChimiqueAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllElementChimique() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveElementChimique() {
        // Given
        ElementChimique toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteElementChimique() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetElementChimiqueById() {
        // Given
        Long idToRetrieve = 1L; // Example ElementChimique ID to retrieve
        ElementChimique expected = new ElementChimique(); // You need to replace ElementChimique with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        ElementChimique result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private ElementChimique constructSample(int i) {
		ElementChimique given = new ElementChimique();
        given.setCode("code-"+i);
        given.setLibelle("libelle-"+i);
        given.setStyle("style-"+i);
        given.setDescription("description-"+i);
        given.setUnite(new Unite(1L));
        return given;
    }

}
