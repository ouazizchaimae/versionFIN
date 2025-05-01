package ma.zyn.app.dao.facade.core.referentiel;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.referentiel.Produit;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.referentiel.Produit;
import java.util.List;


@Repository
public interface ProduitDao extends AbstractRepository<Produit,Long>  {
    Produit findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW Produit(item.id,item.libelle) FROM Produit item")
    List<Produit> findAllOptimized();

}
