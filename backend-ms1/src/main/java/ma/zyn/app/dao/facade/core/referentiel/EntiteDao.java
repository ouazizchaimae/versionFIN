package ma.zyn.app.dao.facade.core.referentiel;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.referentiel.Entite;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.referentiel.Entite;
import java.util.List;


@Repository
public interface EntiteDao extends AbstractRepository<Entite,Long>  {
    Entite findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW Entite(item.id,item.libelle) FROM Entite item")
    List<Entite> findAllOptimized();

}
