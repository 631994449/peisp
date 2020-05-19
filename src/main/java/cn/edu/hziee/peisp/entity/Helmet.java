package cn.edu.hziee.peisp.entity;

import java.util.Date;

public class Helmet {
    private Integer helmetId;

    private Integer workerId;

    /**
    * 剩余电量
    */
    private Integer restElectric;

    /**
    * 开始使用的时间
    */
    private Date producedTime;

    public Integer getHelmetId() {
        return helmetId;
    }

    public void setHelmetId(Integer helmetId) {
        this.helmetId = helmetId;
    }

    public Integer getWorkerId() {
        return workerId;
    }

    public void setWorkerId(Integer workerId) {
        this.workerId = workerId;
    }

    public Integer getRestElectric() {
        return restElectric;
    }

    public void setRestElectric(Integer restElectric) {
        this.restElectric = restElectric;
    }

    public Date getProducedTime() {
        return producedTime;
    }

    public void setProducedTime(Date producedTime) {
        this.producedTime = producedTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", helmetId=").append(helmetId);
        sb.append(", workerId=").append(workerId);
        sb.append(", restElectric=").append(restElectric);
        sb.append(", producedTime=").append(producedTime);
        sb.append("]");
        return sb.toString();
    }
}