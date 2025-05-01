package ma.zyn.app.dao.facade.core.referentiel;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.referentiel.EtatDemande;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.referentiel.EtatDemande;
import java.util.List;


@Repository
public interface EtatDemandeDao extends AbstractRepository<EtatDemande,Long>  {
    EtatDemande findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW EtatDemande(item.id,item.libelle) FROM EtatDemande item")
    List<EtatDemande> findAllOptimized();

}
