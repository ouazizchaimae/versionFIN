package ma.zyn.app.unit.dao.facade.core.referentiel;

import ma.zyn.app.bean.core.referentiel.RatioUnite;
import ma.zyn.app.dao.facade.core.referentiel.RatioUniteDao;

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

import ma.zyn.app.bean.core.referentiel.Entite ;
import ma.zyn.app.bean.core.referentiel.Produit ;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class RatioUniteDaoTest {

@Autowired
    private RatioUniteDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }


    @Test
    void shouldFindById(){
        Long id = 1L;
        RatioUnite entity = new RatioUnite();
        entity.setId(id);
        underTest.save(entity);
        RatioUnite loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        RatioUnite entity = new RatioUnite();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        RatioUnite loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<RatioUnite> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<RatioUnite> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        RatioUnite given = constructSample(1);
        RatioUnite saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private RatioUnite constructSample(int i) {
		RatioUnite given = new RatioUnite();
        given.setEntite(new Entite(1L));
        given.setProduit(new Produit(1L));
        given.setRatio(BigDecimal.TEN);
        return given;
    }

}
