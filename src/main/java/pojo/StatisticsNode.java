package pojo;


public class StatisticsNode {

  private long id;
  private long clusterId;
  private long hostId;
  private String hostName;
  private long osFreeMemory;
  private long cpuPercent;
  private long heapUsed;
  private long gatherSeqNo;
  private String gatherDatetime;
  private long alarmLevel;
  private long status;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getClusterId() {
    return clusterId;
  }

  public void setClusterId(long clusterId) {
    this.clusterId = clusterId;
  }


  public long getHostId() {
    return hostId;
  }

  public void setHostId(long hostId) {
    this.hostId = hostId;
  }


  public String getHostName() {
    return hostName;
  }

  public void setHostName(String hostName) {
    this.hostName = hostName;
  }


  public long getOsFreeMemory() {
    return osFreeMemory;
  }

  public void setOsFreeMemory(long osFreeMemory) {
    this.osFreeMemory = osFreeMemory;
  }


  public long getCpuPercent() {
    return cpuPercent;
  }

  public void setCpuPercent(long cpuPercent) {
    this.cpuPercent = cpuPercent;
  }


  public long getHeapUsed() {
    return heapUsed;
  }

  public void setHeapUsed(long heapUsed) {
    this.heapUsed = heapUsed;
  }


  public long getGatherSeqNo() {
    return gatherSeqNo;
  }

  public void setGatherSeqNo(long gatherSeqNo) {
    this.gatherSeqNo = gatherSeqNo;
  }


  public String getGatherDatetime() {
    return gatherDatetime;
  }

  public void setGatherDatetime(String gatherDatetime) {
    this.gatherDatetime = gatherDatetime;
  }


  public long getAlarmLevel() {
    return alarmLevel;
  }

  public void setAlarmLevel(long alarmLevel) {
    this.alarmLevel = alarmLevel;
  }


  public long getStatus() {
    return status;
  }

  public void setStatus(long status) {
    this.status = status;
  }

}
