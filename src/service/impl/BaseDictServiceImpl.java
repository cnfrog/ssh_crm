package service.impl;

import dao.BaseDictDao;
import domain.BaseDict;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import service.BaseDictService;

import javax.annotation.Resource;
import java.util.List;

@Service("baseDictService")
@Transactional(isolation = Isolation.REPEATABLE_READ,propagation = Propagation.NEVER,readOnly = false)
public class BaseDictServiceImpl implements BaseDictService {

    @Resource(name = "baseDictDao")
    private BaseDictDao bdd;

    @Override
    public List<BaseDict> getListByTypeCode(String dict_type_code) {
        return bdd.getListByTypeCode(dict_type_code);
    }


    public void setBdd(BaseDictDao bdd) {
        this.bdd = bdd;
    }
}
