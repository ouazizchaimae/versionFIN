package ma.zyn.app.unit.dao.facade.core.expedition;

import ma.zyn.app.bean.core.expedition.CharteChimiqueDetail;
import ma.zyn.app.dao.facade.core.expedition.CharteChimiqueDetailDao;

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

import ma.zyn.app.bean.core.referentiel.ElementChimique ;
import ma.zyn.app.bean.core.expedition.CharteChimique ;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class CharteChimiqueDetailDaoTest {

@Autowired
    private CharteChimiqueDetailDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }


    @Test
    void shouldFindById(){
        Long id = 1L;
        CharteChimiqueDetail entity = new CharteChimiqueDetail();
        entity.setId(id);
        underTest.save(entity);
        CharteChimiqueDetail loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        CharteChimiqueDetail entity = new CharteChimiqueDetail();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        CharteChimiqueDetail loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<CharteChimiqueDetail> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<CharteChimiqueDetail> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        CharteChimiqueDetail given = constructSample(1);
        CharteChimiqueDetail saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
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
