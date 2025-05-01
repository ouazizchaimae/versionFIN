package ma.zyn.app.dao.facade.core.referentiel;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.referentiel.TypeClient;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.referentiel.TypeClient;
import java.util.List;


@Repository
public interface TypeClientDao extends AbstractRepository<TypeClient,Long>  {
    TypeClient findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW TypeClient(item.id,item.libelle) FROM TypeClient item")
    List<TypeClient> findAllOptimized();

}
