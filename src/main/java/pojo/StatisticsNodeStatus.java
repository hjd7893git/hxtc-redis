package pojo;


public class StatisticsNodeStatus {

  private long id;
  private long hostId;
  private long clusterId;
  private String startTime;
  private String endTime;
  private long endGatherSeqNo;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getHostId() {
    return hostId;
  }

  public void setHostId(long hostId) {
    this.hostId = hostId;
  }


  public long getClusterId() {
    return clusterId;
  }

  public void setClusterId(long clusterId) {
    this.clusterId = clusterId;
  }


  public String getStartTime() {
    return startTime;
  }

  public void setStartTime(String startTime) {
    this.startTime = startTime;
  }


  public String getEndTime() {
    return endTime;
  }

  public void setEndTime(String endTime) {
    this.endTime = endTime;
  }


  public long getEndGatherSeqNo() {
    return endGatherSeqNo;
  }

  public void setEndGatherSeqNo(long endGatherSeqNo) {
    this.endGatherSeqNo = endGatherSeqNo;
  }

}
