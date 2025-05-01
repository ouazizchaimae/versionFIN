package ma.zyn.app.dao.facade.core.referentiel;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.referentiel.Unite;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.referentiel.Unite;
import java.util.List;


@Repository
public interface UniteDao extends AbstractRepository<Unite,Long>  {
    Unite findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW Unite(item.id,item.libelle) FROM Unite item")
    List<Unite> findAllOptimized();

}
