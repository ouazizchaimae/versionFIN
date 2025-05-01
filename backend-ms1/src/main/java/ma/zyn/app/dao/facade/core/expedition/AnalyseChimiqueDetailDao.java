package ma.zyn.app.dao.facade.core.expedition;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.expedition.AnalyseChimiqueDetail;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface AnalyseChimiqueDetailDao extends AbstractRepository<AnalyseChimiqueDetail,Long>  {

    List<AnalyseChimiqueDetail> findByElementChimiqueCode(String code);
    List<AnalyseChimiqueDetail> findByElementChimiqueId(Long id);
    int deleteByElementChimiqueId(Long id);
    int deleteByElementChimiqueCode(String code);
    long countByElementChimiqueCode(String code);
    List<AnalyseChimiqueDetail> findByAnalyseChimiqueId(Long id);
    int deleteByAnalyseChimiqueId(Long id);
    long countByAnalyseChimiqueCode(String code);

    @Query("SELECT NEW AnalyseChimiqueDetail(item.id,item.libelle) FROM AnalyseChimiqueDetail item")
    List<AnalyseChimiqueDetail> findAllOptimized();

}
