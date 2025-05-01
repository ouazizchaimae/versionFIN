package ma.zyn.app.unit.service.impl.admin.expedition;

import ma.zyn.app.bean.core.expedition.CharteChimiqueDetail;
import ma.zyn.app.dao.facade.core.expedition.CharteChimiqueDetailDao;
import ma.zyn.app.service.impl.admin.expedition.CharteChimiqueDetailAdminServiceImpl;

import ma.zyn.app.bean.core.referentiel.ElementChimique ;
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
class CharteChimiqueDetailAdminServiceImplTest {

    @Mock
    private CharteChimiqueDetailDao repository;
    private AutoCloseable autoCloseable;
    private CharteChimiqueDetailAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new CharteChimiqueDetailAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllCharteChimiqueDetail() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveCharteChimiqueDetail() {
        // Given
        CharteChimiqueDetail toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteCharteChimiqueDetail() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetCharteChimiqueDetailById() {
        // Given
        Long idToRetrieve = 1L; // Example CharteChimiqueDetail ID to retrieve
        CharteChimiqueDetail expected = new CharteChimiqueDetail(); // You need to replace CharteChimiqueDetail with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        CharteChimiqueDetail result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private CharteChimiqueDetail constructSample(int i) {
		CharteChimiqueDetail given = new CharteChimiqueDetail();
        given.setLibelle("libelle-"+i);
        given.setDescription("description-"+i);
        given.setElementChimique(new ElementChimique(1L));
        given.setMinimum(BigDecimal.TEN);
        given.setMaximum(BigDecimal.TEN);
        given.setAverage(BigDecimal.TEN);
        given.setMethodeAnalyse("methodeAnalyse-"+i);
        given.setUnite("unite-"+i);
        given.setCharteChimique(new CharteChimique(1L));
        return given;
    }

}
