package dao;

import domain.BaseDict;

import java.util.List;

public interface BaseDictDao extends BaseDao<BaseDict>{
    List<BaseDict> getListByTypeCode(String dict_type_code);
}
