package cn.edu.hziee.peisp.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import cn.edu.hziee.peisp.mapper.IllegalMapper;
import cn.edu.hziee.peisp.entity.Illegal;

@Service
public class IllegalService {

    @Resource
    private IllegalMapper illegalMapper;


    public int deleteByPrimaryKey(Integer id) {
        return illegalMapper.deleteByPrimaryKey(id);
    }


    public int insert(Illegal record) {
        return illegalMapper.insert(record);
    }


    public int insertSelective(Illegal record) {
        return illegalMapper.insertSelective(record);
    }


    public Illegal selectByPrimaryKey(Integer id) {
        return illegalMapper.selectByPrimaryKey(id);
    }


    public int updateByPrimaryKeySelective(Illegal record) {
        return illegalMapper.updateByPrimaryKeySelective(record);
    }


    public int updateByPrimaryKey(Illegal record) {
        return illegalMapper.updateByPrimaryKey(record);
    }

}




