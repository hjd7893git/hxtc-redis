package pojo;


public class StatisticsExchange {

  private long id;
  private String appSymbol;
  private String appIp;
  private long clusterId;
  private long hostId;
  private String command;
  private String event;
  private String errorCode;
  private String groupName;
  private String machineIp;
  private long machinePort;
  private long times;
  private long gatherSeqNo;
  private String gatherDatetime;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getAppSymbol() {
    return appSymbol;
  }

  public void setAppSymbol(String appSymbol) {
    this.appSymbol = appSymbol;
  }


  public String getAppIp() {
    return appIp;
  }

  public void setAppIp(String appIp) {
    this.appIp = appIp;
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


  public String getCommand() {
    return command;
  }

  public void setCommand(String command) {
    this.command = command;
  }


  public String getEvent() {
    return event;
  }

  public void setEvent(String event) {
    this.event = event;
  }


  public String getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(String errorCode) {
    this.errorCode = errorCode;
  }


  public String getGroupName() {
    return groupName;
  }

  public void setGroupName(String groupName) {
    this.groupName = groupName;
  }


  public String getMachineIp() {
    return machineIp;
  }

  public void setMachineIp(String machineIp) {
    this.machineIp = machineIp;
  }


  public long getMachinePort() {
    return machinePort;
  }

  public void setMachinePort(long machinePort) {
    this.machinePort = machinePort;
  }


  public long getTimes() {
    return times;
  }

  public void setTimes(long times) {
    this.times = times;
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

}
