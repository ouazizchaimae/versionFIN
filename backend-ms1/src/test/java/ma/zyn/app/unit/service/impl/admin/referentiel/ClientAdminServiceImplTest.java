package ma.zyn.app.unit.service.impl.admin.referentiel;

import ma.zyn.app.bean.core.referentiel.Client;
import ma.zyn.app.dao.facade.core.referentiel.ClientDao;
import ma.zyn.app.service.impl.admin.referentiel.ClientAdminServiceImpl;

import ma.zyn.app.bean.core.referentiel.TypeClient ;
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
class ClientAdminServiceImplTest {

    @Mock
    private ClientDao repository;
    private AutoCloseable autoCloseable;
    private ClientAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new ClientAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllClient() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveClient() {
        // Given
        Client toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteClient() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetClientById() {
        // Given
        Long idToRetrieve = 1L; // Example Client ID to retrieve
        Client expected = new Client(); // You need to replace Client with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        Client result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private Client constructSample(int i) {
		Client given = new Client();
        given.setLibelle("libelle-"+i);
        given.setCode("code-"+i);
        given.setDescription("description-"+i);
        given.setTypeClient(new TypeClient(1L));
        return given;
    }

}
