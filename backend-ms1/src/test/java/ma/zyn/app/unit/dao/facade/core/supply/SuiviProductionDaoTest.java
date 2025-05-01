package ma.zyn.app.unit.dao.facade.core.supply;

import ma.zyn.app.bean.core.supply.SuiviProduction;
import ma.zyn.app.dao.facade.core.supply.SuiviProductionDao;

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

import ma.zyn.app.bean.core.referentiel.Unite ;
import ma.zyn.app.bean.core.referentiel.StadeOperatoire ;
import ma.zyn.app.bean.core.referentiel.Produit ;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class SuiviProductionDaoTest {

@Autowired
    private SuiviProductionDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }

    @Test
    void shouldFindByCode(){
        String code = "code-1";
        SuiviProduction entity = new SuiviProduction();
        entity.setCode(code);
        underTest.save(entity);
        SuiviProduction loaded = underTest.findByCode(code);
        assertThat(loaded.getCode()).isEqualTo(code);
    }

    @Test
    void shouldDeleteByCode() {
        String code = "code-12345678";
        int result = underTest.deleteByCode(code);

        SuiviProduction loaded = underTest.findByCode(code);
        assertThat(loaded).isNull();
        assertThat(result).isEqualTo(0);
    }

    @Test
    void shouldFindById(){
        Long id = 1L;
        SuiviProduction entity = new SuiviProduction();
        entity.setId(id);
        underTest.save(entity);
        SuiviProduction loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        SuiviProduction entity = new SuiviProduction();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        SuiviProduction loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<SuiviProduction> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<SuiviProduction> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        SuiviProduction given = constructSample(1);
        SuiviProduction saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private SuiviProduction constructSample(int i) {
		SuiviProduction given = new SuiviProduction();
        given.setCode("code-"+i);
        given.setLibelle("libelle-"+i);
        given.setDescription("description-"+i);
        given.setJour(LocalDateTime.now());
        given.setVolume(BigDecimal.TEN);
        given.setTsm(BigDecimal.TEN);
        given.setProduit(new Produit(1L));
        given.setStadeOperatoire(new StadeOperatoire(1L));
        given.setUnite(new Unite(1L));
        return given;
    }

}
