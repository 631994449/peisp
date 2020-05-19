package cn.edu.hziee.peisp.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import cn.edu.hziee.peisp.entity.Helmet;
import cn.edu.hziee.peisp.mapper.HelmetMapper;
@Service
public class HelmetService{

    @Resource
    private HelmetMapper helmetMapper;

    
    public int deleteByPrimaryKey(Integer helmetId) {
        return helmetMapper.deleteByPrimaryKey(helmetId);
    }

    
    public int insert(Helmet record) {
        return helmetMapper.insert(record);
    }

    
    public int insertSelective(Helmet record) {
        return helmetMapper.insertSelective(record);
    }

    
    public Helmet selectByPrimaryKey(Integer helmetId) {
        return helmetMapper.selectByPrimaryKey(helmetId);
    }

    
    public int updateByPrimaryKeySelective(Helmet record) {
        return helmetMapper.updateByPrimaryKeySelective(record);
    }

    
    public int updateByPrimaryKey(Helmet record) {
        return helmetMapper.updateByPrimaryKey(record);
    }

}
