package ma.zyn.app.unit.service.impl.admin.referentiel;

import ma.zyn.app.bean.core.referentiel.TypeClient;
import ma.zyn.app.dao.facade.core.referentiel.TypeClientDao;
import ma.zyn.app.service.impl.admin.referentiel.TypeClientAdminServiceImpl;

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
class TypeClientAdminServiceImplTest {

    @Mock
    private TypeClientDao repository;
    private AutoCloseable autoCloseable;
    private TypeClientAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new TypeClientAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllTypeClient() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveTypeClient() {
        // Given
        TypeClient toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteTypeClient() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetTypeClientById() {
        // Given
        Long idToRetrieve = 1L; // Example TypeClient ID to retrieve
        TypeClient expected = new TypeClient(); // You need to replace TypeClient with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        TypeClient result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private TypeClient constructSample(int i) {
		TypeClient given = new TypeClient();
        given.setLibelle("libelle-"+i);
        given.setCode("code-"+i);
        given.setStyle("style-"+i);
        given.setDescription("description-"+i);
        return given;
    }

}
