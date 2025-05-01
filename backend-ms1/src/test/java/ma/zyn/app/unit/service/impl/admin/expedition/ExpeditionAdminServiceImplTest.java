package ma.zyn.app.unit.service.impl.admin.expedition;

import ma.zyn.app.bean.core.expedition.Expedition;
import ma.zyn.app.dao.facade.core.expedition.ExpeditionDao;
import ma.zyn.app.service.impl.admin.expedition.ExpeditionAdminServiceImpl;

import ma.zyn.app.bean.core.expedition.ExpeditionProduit ;
import ma.zyn.app.bean.core.expedition.AnalyseChimique ;
import ma.zyn.app.bean.core.expedition.TypeExpedition ;
import ma.zyn.app.bean.core.referentiel.Client ;
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
class ExpeditionAdminServiceImplTest {

    @Mock
    private ExpeditionDao repository;
    private AutoCloseable autoCloseable;
    private ExpeditionAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new ExpeditionAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllExpedition() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveExpedition() {
        // Given
        Expedition toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteExpedition() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetExpeditionById() {
        // Given
        Long idToRetrieve = 1L; // Example Expedition ID to retrieve
        Expedition expected = new Expedition(); // You need to replace Expedition with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        Expedition result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private Expedition constructSample(int i) {
		Expedition given = new Expedition();
        given.setCode("code-"+i);
        given.setLibelle("libelle-"+i);
        given.setDescription("description-"+i);
        given.setClient(new Client(1L));
        given.setTypeExpedition(new TypeExpedition(1L));
        List<ExpeditionProduit> expeditionProduits = IntStream.rangeClosed(1, 3)
                                             .mapToObj(id -> {
                                                ExpeditionProduit element = new ExpeditionProduit();
                                                element.setId((long)id);
                                                element.setCode("code"+id);
                                                element.setLibelle("libelle"+id);
                                                element.setDescription("description"+id);
                                                element.setAnalyseChimique(new AnalyseChimique(Long.valueOf(4)));
                                                element.setCharteChimique(new CharteChimique(Long.valueOf(5)));
                                                return element;
                                             })
                                             .collect(Collectors.toList());
        given.setExpeditionProduits(expeditionProduits);
        return given;
    }

}
