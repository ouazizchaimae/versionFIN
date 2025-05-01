package ma.zyn.app.unit.dao.facade.core.referentiel;

import ma.zyn.app.bean.core.referentiel.StadeOperatoireProduit;
import ma.zyn.app.dao.facade.core.referentiel.StadeOperatoireProduitDao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.BeforeEach;

import java.math.BigDecimal;
import java.util.List;

import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.IntStream;
import java.time.LocalDateTime;

import ma.zyn.app.bean.core.referentiel.StadeOperatoire ;
import ma.zyn.app.bean.core.referentiel.Produit ;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class StadeOperatoireProduitDaoTest {

@Autowired
    private StadeOperatoireProduitDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }


    @Test
    void shouldFindById(){
        Long id = 1L;
        StadeOperatoireProduit entity = new StadeOperatoireProduit();
        entity.setId(id);
        underTest.save(entity);
        StadeOperatoireProduit loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        StadeOperatoireProduit entity = new StadeOperatoireProduit();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        StadeOperatoireProduit loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<StadeOperatoireProduit> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<StadeOperatoireProduit> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        StadeOperatoireProduit given = constructSample(1);
        StadeOperatoireProduit saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private StadeOperatoireProduit constructSample(int i) {
		StadeOperatoireProduit given = new StadeOperatoireProduit();
        given.setStadeOperatoire(new StadeOperatoire(1L));
        given.setProduit(new Produit(1L));
        return given;
    }

}
