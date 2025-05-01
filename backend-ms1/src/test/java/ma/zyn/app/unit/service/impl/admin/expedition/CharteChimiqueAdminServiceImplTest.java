package ma.zyn.app.unit.service.impl.admin.expedition;

import ma.zyn.app.bean.core.expedition.CharteChimique;
import ma.zyn.app.dao.facade.core.expedition.CharteChimiqueDao;
import ma.zyn.app.service.impl.admin.expedition.CharteChimiqueAdminServiceImpl;

import ma.zyn.app.bean.core.expedition.CharteChimiqueDetail ;
import ma.zyn.app.bean.core.referentiel.ElementChimique ;
import ma.zyn.app.bean.core.referentiel.ProduitMarchand ;
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
class CharteChimiqueAdminServiceImplTest {

    @Mock
    private CharteChimiqueDao repository;
    private AutoCloseable autoCloseable;
    private CharteChimiqueAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new CharteChimiqueAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllCharteChimique() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveCharteChimique() {
        // Given
        CharteChimique toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteCharteChimique() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetCharteChimiqueById() {
        // Given
        Long idToRetrieve = 1L; // Example CharteChimique ID to retrieve
        CharteChimique expected = new CharteChimique(); // You need to replace CharteChimique with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        CharteChimique result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private CharteChimique constructSample(int i) {
		CharteChimique given = new CharteChimique();
        given.setCode("code-"+i);
        given.setLibelle("libelle-"+i);
        given.setDescription("description-"+i);
        given.setClient(new Client(1L));
        given.setProduitMarchand(new ProduitMarchand(1L));
        List<CharteChimiqueDetail> charteChimiqueDetails = IntStream.rangeClosed(1, 3)
                                             .mapToObj(id -> {
                                                CharteChimiqueDetail element = new CharteChimiqueDetail();
                                                element.setId((long)id);
                                                element.setLibelle("libelle"+id);
                                                element.setDescription("description"+id);
                                                element.setElementChimique(new ElementChimique(Long.valueOf(3)));
                                                element.setMinimum(new BigDecimal(4*10));
                                                element.setMaximum(new BigDecimal(5*10));
                                                element.setAverage(new BigDecimal(6*10));
                                                element.setMethodeAnalyse("methodeAnalyse"+id);
                                                element.setUnite("unite"+id);
                                                element.setCharteChimique(new CharteChimique(Long.valueOf(9)));
                                                return element;
                                             })
                                             .collect(Collectors.toList());
        given.setCharteChimiqueDetails(charteChimiqueDetails);
        return given;
    }

}
