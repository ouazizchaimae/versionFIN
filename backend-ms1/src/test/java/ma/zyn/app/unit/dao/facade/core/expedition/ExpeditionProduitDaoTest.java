package ma.zyn.app.unit.dao.facade.core.expedition;

import ma.zyn.app.bean.core.expedition.ExpeditionProduit;
import ma.zyn.app.dao.facade.core.expedition.ExpeditionProduitDao;

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

import ma.zyn.app.bean.core.expedition.AnalyseChimique ;
import ma.zyn.app.bean.core.expedition.CharteChimique ;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class ExpeditionProduitDaoTest {

@Autowired
    private ExpeditionProduitDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }

    @Test
    void shouldFindByCode(){
        String code = "code-1";
        ExpeditionProduit entity = new ExpeditionProduit();
        entity.setCode(code);
        underTest.save(entity);
        ExpeditionProduit loaded = underTest.findByCode(code);
        assertThat(loaded.getCode()).isEqualTo(code);
    }

    @Test
    void shouldDeleteByCode() {
        String code = "code-12345678";
        int result = underTest.deleteByCode(code);

        ExpeditionProduit loaded = underTest.findByCode(code);
        assertThat(loaded).isNull();
        assertThat(result).isEqualTo(0);
    }

    @Test
    void shouldFindById(){
        Long id = 1L;
        ExpeditionProduit entity = new ExpeditionProduit();
        entity.setId(id);
        underTest.save(entity);
        ExpeditionProduit loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        ExpeditionProduit entity = new ExpeditionProduit();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        ExpeditionProduit loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<ExpeditionProduit> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<ExpeditionProduit> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        ExpeditionProduit given = constructSample(1);
        ExpeditionProduit saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private ExpeditionProduit constructSample(int i) {
		ExpeditionProduit given = new ExpeditionProduit();
        given.setCode("code-"+i);
        given.setLibelle("libelle-"+i);
        given.setDescription("description-"+i);
        given.setAnalyseChimique(new AnalyseChimique(1L));
        given.setCharteChimique(new CharteChimique(1L));
        return given;
    }

}
