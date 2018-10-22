package com.chandlertu.minion;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConnectResultService {

  @Autowired
  private ReachResultRepository reachResultRepository;

  @Autowired
  private ConnectResultRepository connectResultRepository;

  public ConnectResult connect(String hostname, int port) {
    ConnectResult connectResult = new ConnectResult();
    connectResult.setHostAddress(hostname);
    connectResult.setPort(port);
    boolean isConnected;
    try (Socket socket = new Socket()) {
      socket.connect(new InetSocketAddress(hostname, port), 1000);
      isConnected = true;
    } catch (Exception e) {
      isConnected = false;
    }
    connectResult.setIsConnected(isConnected);
    connectResult.setUpdateTime(new Date());
    return connectResult;
  }

  public void connect() {
    int[] ports = { 22, 2223, 5631 };
    List<ReachResult> reachResults = reachResultRepository.findByIsReachable(true);
    for (ReachResult reachResult : reachResults) {
      for (int port : ports) {
        ConnectResult connectResult = connect(reachResult.getHostAddress(), port);
        connectResultRepository.save(connectResult);
      }
    }
  }

}
