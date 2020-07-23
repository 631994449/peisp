package cn.edu.hziee.peisp.entity;

import java.util.Date;

public class Illegal {
    private Integer id;

    private String workerId;

    private String workerName;

    private String reason;

    private Date time;

    private String imgDir;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWorkerId() {
        return workerId;
    }

    public void setWorkerId(String workerId) {
        this.workerId = workerId;
    }

    public String getWorkerName() {
        return workerName;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getImgDir() {
        return imgDir;
    }

    public void setImgDir(String imgDir) {
        this.imgDir = imgDir;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", workerId=").append(workerId);
        sb.append(", workerName=").append(workerName);
        sb.append(", reason=").append(reason);
        sb.append(", time=").append(time);
        sb.append(", imgDir=").append(imgDir);
        sb.append("]");
        return sb.toString();
    }
}