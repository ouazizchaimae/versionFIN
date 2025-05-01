package ma.zyn.app.unit.dao.facade.core.expedition;

import ma.zyn.app.bean.core.expedition.CharteChimique;
import ma.zyn.app.dao.facade.core.expedition.CharteChimiqueDao;

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
import ma.zyn.app.bean.core.referentiel.Client ;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class CharteChimiqueDaoTest {

@Autowired
    private CharteChimiqueDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }

    @Test
    void shouldFindByCode(){
        String code = "code-1";
        CharteChimique entity = new CharteChimique();
        entity.setCode(code);
        underTest.save(entity);
        CharteChimique loaded = underTest.findByCode(code);
        assertThat(loaded.getCode()).isEqualTo(code);
    }

    @Test
    void shouldDeleteByCode() {
        String code = "code-12345678";
        int result = underTest.deleteByCode(code);

        CharteChimique loaded = underTest.findByCode(code);
        assertThat(loaded).isNull();
        assertThat(result).isEqualTo(0);
    }

    @Test
    void shouldFindById(){
        Long id = 1L;
        CharteChimique entity = new CharteChimique();
        entity.setId(id);
        underTest.save(entity);
        CharteChimique loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        CharteChimique entity = new CharteChimique();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        CharteChimique loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<CharteChimique> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<CharteChimique> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        CharteChimique given = constructSample(1);
        CharteChimique saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private CharteChimique constructSample(int i) {
		CharteChimique given = new CharteChimique();
        given.setCode("code-"+i);
        given.setLibelle("libelle-"+i);
        given.setDescription("description-"+i);
        given.setClient(new Client(1L));
        given.setProduitMarchand(new ProduitMarchand(1L));
        return given;
    }

}
