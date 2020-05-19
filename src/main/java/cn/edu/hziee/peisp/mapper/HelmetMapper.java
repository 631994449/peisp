package cn.edu.hziee.peisp.mapper;

import cn.edu.hziee.peisp.entity.Helmet;

public interface HelmetMapper {
    int deleteByPrimaryKey(Integer helmetId);

    int insert(Helmet record);

    int insertSelective(Helmet record);

    Helmet selectByPrimaryKey(Integer helmetId);

    int updateByPrimaryKeySelective(Helmet record);

    int updateByPrimaryKey(Helmet record);
}