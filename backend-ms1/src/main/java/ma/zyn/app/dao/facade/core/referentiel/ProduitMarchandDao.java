package ma.zyn.app.dao.facade.core.referentiel;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.referentiel.ProduitMarchand;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.referentiel.ProduitMarchand;
import java.util.List;


@Repository
public interface ProduitMarchandDao extends AbstractRepository<ProduitMarchand,Long>  {
    ProduitMarchand findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW ProduitMarchand(item.id,item.libelle) FROM ProduitMarchand item")
    List<ProduitMarchand> findAllOptimized();

}
