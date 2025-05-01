package ma.zyn.app.dao.facade.core.expedition;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.expedition.CharteChimiqueDetail;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface CharteChimiqueDetailDao extends AbstractRepository<CharteChimiqueDetail,Long>  {

    List<CharteChimiqueDetail> findByElementChimiqueCode(String code);
    List<CharteChimiqueDetail> findByElementChimiqueId(Long id);
    int deleteByElementChimiqueId(Long id);
    int deleteByElementChimiqueCode(String code);
    long countByElementChimiqueCode(String code);
    List<CharteChimiqueDetail> findByCharteChimiqueId(Long id);
    int deleteByCharteChimiqueId(Long id);
    long countByCharteChimiqueCode(String code);

    @Query("SELECT NEW CharteChimiqueDetail(item.id,item.libelle) FROM CharteChimiqueDetail item")
    List<CharteChimiqueDetail> findAllOptimized();

}
