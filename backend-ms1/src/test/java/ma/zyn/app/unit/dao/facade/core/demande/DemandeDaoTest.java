package ma.zyn.app.unit.dao.facade.core.demande;

import ma.zyn.app.bean.core.demande.Demande;
import ma.zyn.app.dao.facade.core.demande.DemandeDao;

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

import ma.zyn.app.bean.core.referentiel.EtatDemande ;
import ma.zyn.app.bean.core.referentiel.ProduitMarchand ;
import ma.zyn.app.bean.core.referentiel.TypeDemande ;
import ma.zyn.app.bean.core.referentiel.Client ;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class DemandeDaoTest {

@Autowired
    private DemandeDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }

    @Test
    void shouldFindByCode(){
        String code = "code-1";
        Demande entity = new Demande();
        entity.setCode(code);
        underTest.save(entity);
        Demande loaded = underTest.findByCode(code);
        assertThat(loaded.getCode()).isEqualTo(code);
    }

    @Test
    void shouldDeleteByCode() {
        String code = "code-12345678";
        int result = underTest.deleteByCode(code);

        Demande loaded = underTest.findByCode(code);
        assertThat(loaded).isNull();
        assertThat(result).isEqualTo(0);
    }

    @Test
    void shouldFindById(){
        Long id = 1L;
        Demande entity = new Demande();
        entity.setId(id);
        underTest.save(entity);
        Demande loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        Demande entity = new Demande();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        Demande loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<Demande> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<Demande> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        Demande given = constructSample(1);
        Demande saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private Demande constructSample(int i) {
		Demande given = new Demande();
        given.setCode("code-"+i);
        given.setLibelle("libelle-"+i);
        given.setDescription("description-"+i);
        given.setProduitMarchand(new ProduitMarchand(1L));
        given.setClient(new Client(1L));
        given.setDateDemande(LocalDateTime.now());
        given.setDateExpedition(LocalDateTime.now());
        given.setVolume(BigDecimal.TEN);
        given.setTypeDemande(new TypeDemande(1L));
        given.setEtatDemande(new EtatDemande(1L));
        given.setActionEntreprise("actionEntreprise-"+i);
        given.setTrg("trg-"+i);
        given.setCause("cause-"+i);
        given.setCommentaire("commentaire-"+i);
        return given;
    }

}
