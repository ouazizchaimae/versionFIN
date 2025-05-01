package ma.zyn.app.unit.dao.facade.core.expedition;

import ma.zyn.app.bean.core.expedition.AnalyseChimique;
import ma.zyn.app.dao.facade.core.expedition.AnalyseChimiqueDao;

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

import ma.zyn.app.bean.core.referentiel.ProduitMarchand ;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class AnalyseChimiqueDaoTest {

@Autowired
    private AnalyseChimiqueDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }

    @Test
    void shouldFindByCode(){
        String code = "code-1";
        AnalyseChimique entity = new AnalyseChimique();
        entity.setCode(code);
        underTest.save(entity);
        AnalyseChimique loaded = underTest.findByCode(code);
        assertThat(loaded.getCode()).isEqualTo(code);
    }

    @Test
    void shouldDeleteByCode() {
        String code = "code-12345678";
        int result = underTest.deleteByCode(code);

        AnalyseChimique loaded = underTest.findByCode(code);
        assertThat(loaded).isNull();
        assertThat(result).isEqualTo(0);
    }

    @Test
    void shouldFindById(){
        Long id = 1L;
        AnalyseChimique entity = new AnalyseChimique();
        entity.setId(id);
        underTest.save(entity);
        AnalyseChimique loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        AnalyseChimique entity = new AnalyseChimique();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        AnalyseChimique loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<AnalyseChimique> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<AnalyseChimique> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        AnalyseChimique given = constructSample(1);
        AnalyseChimique saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private AnalyseChimique constructSample(int i) {
		AnalyseChimique given = new AnalyseChimique();
        given.setCode("code-"+i);
        given.setLibelle("libelle-"+i);
        given.setDescription("description-"+i);
        given.setProduitMarchand(new ProduitMarchand(1L));
        return given;
    }

}
