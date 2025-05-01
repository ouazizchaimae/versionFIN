package ma.zyn.app.dao.facade.core.referentiel;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.referentiel.Client;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.referentiel.Client;
import java.util.List;


@Repository
public interface ClientDao extends AbstractRepository<Client,Long>  {
    Client findByCode(String code);
    int deleteByCode(String code);

    List<Client> findByTypeClientCode(String code);
    List<Client> findByTypeClientId(Long id);
    int deleteByTypeClientId(Long id);
    int deleteByTypeClientCode(String code);
    long countByTypeClientCode(String code);

    @Query("SELECT NEW Client(item.id,item.libelle) FROM Client item")
    List<Client> findAllOptimized();

}
