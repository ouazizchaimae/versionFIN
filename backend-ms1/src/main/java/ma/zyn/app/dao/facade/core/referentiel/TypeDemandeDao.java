package ma.zyn.app.dao.facade.core.referentiel;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.referentiel.TypeDemande;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.referentiel.TypeDemande;
import java.util.List;


@Repository
public interface TypeDemandeDao extends AbstractRepository<TypeDemande,Long>  {
    TypeDemande findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW TypeDemande(item.id,item.libelle) FROM TypeDemande item")
    List<TypeDemande> findAllOptimized();

}
