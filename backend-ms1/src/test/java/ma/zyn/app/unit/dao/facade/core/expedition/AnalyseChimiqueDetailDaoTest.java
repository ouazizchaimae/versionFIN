package ma.zyn.app.unit.dao.facade.core.expedition;

import ma.zyn.app.bean.core.expedition.AnalyseChimiqueDetail;
import ma.zyn.app.dao.facade.core.expedition.AnalyseChimiqueDetailDao;

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
import ma.zyn.app.bean.core.expedition.AnalyseChimique ;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class AnalyseChimiqueDetailDaoTest {

@Autowired
    private AnalyseChimiqueDetailDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }


    @Test
    void shouldFindById(){
        Long id = 1L;
        AnalyseChimiqueDetail entity = new AnalyseChimiqueDetail();
        entity.setId(id);
        underTest.save(entity);
        AnalyseChimiqueDetail loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        AnalyseChimiqueDetail entity = new AnalyseChimiqueDetail();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        AnalyseChimiqueDetail loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<AnalyseChimiqueDetail> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<AnalyseChimiqueDetail> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        AnalyseChimiqueDetail given = constructSample(1);
        AnalyseChimiqueDetail saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private AnalyseChimiqueDetail constructSample(int i) {
		AnalyseChimiqueDetail given = new AnalyseChimiqueDetail();
        given.setLibelle("libelle-"+i);
        given.setDescription("description-"+i);
        given.setElementChimique(new ElementChimique(1L));
        given.setValeur(BigDecimal.TEN);
        given.setConformite(false);
        given.setSurqualite(false);
        given.setAnalyseChimique(new AnalyseChimique(1L));
        return given;
    }

}
