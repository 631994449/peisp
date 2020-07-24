package cn.edu.hziee.peisp.mapper;

import cn.edu.hziee.peisp.entity.Illegal;

import java.util.List;

public interface IllegalMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Illegal record);

    int insertSelective(Illegal record);

    Illegal selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Illegal record);

    int updateByPrimaryKey(Illegal record);

    List<Illegal> getMax5Illegal();
}