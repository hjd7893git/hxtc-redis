package pojo;


public class StatisticsMachine {

  private long id;
  private long clusterId;
  private long groupId;
  private long machineId;
  private String machineIp;
  private long freeMemory;
  private long usedMemory;
  private long totalJiffies;
  private long busyJiffies;
  private long cpuPercent;
  private long connectionQuantity;
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


  public long getGroupId() {
    return groupId;
  }

  public void setGroupId(long groupId) {
    this.groupId = groupId;
  }


  public long getMachineId() {
    return machineId;
  }

  public void setMachineId(long machineId) {
    this.machineId = machineId;
  }


  public String getMachineIp() {
    return machineIp;
  }

  public void setMachineIp(String machineIp) {
    this.machineIp = machineIp;
  }


  public long getFreeMemory() {
    return freeMemory;
  }

  public void setFreeMemory(long freeMemory) {
    this.freeMemory = freeMemory;
  }


  public long getUsedMemory() {
    return usedMemory;
  }

  public void setUsedMemory(long usedMemory) {
    this.usedMemory = usedMemory;
  }


  public long getTotalJiffies() {
    return totalJiffies;
  }

  public void setTotalJiffies(long totalJiffies) {
    this.totalJiffies = totalJiffies;
  }


  public long getBusyJiffies() {
    return busyJiffies;
  }

  public void setBusyJiffies(long busyJiffies) {
    this.busyJiffies = busyJiffies;
  }


  public long getCpuPercent() {
    return cpuPercent;
  }

  public void setCpuPercent(long cpuPercent) {
    this.cpuPercent = cpuPercent;
  }


  public long getConnectionQuantity() {
    return connectionQuantity;
  }

  public void setConnectionQuantity(long connectionQuantity) {
    this.connectionQuantity = connectionQuantity;
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
