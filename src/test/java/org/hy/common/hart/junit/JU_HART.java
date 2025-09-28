package org.hy.common.hart.junit;

import org.junit.Test;

import com.fazecast.jSerialComm.SerialPort;





public class JU_HART
{
    
    @Test
    public void test_001_HART() 
    {
        // 获取所有串口
        SerialPort[] ports = SerialPort.getCommPorts();
        
        // 假设我们选择了第一个串口
        SerialPort port = ports[0];

        // 配置串口
        port.setComPortParameters(9600, 8, 1, SerialPort.NO_PARITY);  // 9600波特率，8数据位，1停止位，0无校验
        port.setComPortTimeouts(SerialPort.TIMEOUT_READ_BLOCKING, 0, 0);  // 阻塞读取模式

        // 打开串口
        if ( port.openPort() )
        {
            System.out.println("串口已打开：" + port.getSystemPortName());
            // 在这里添加与HART设备的具体通信代码
            // 关闭串口
            port.closePort();
        }
        else
        {
            System.out.println("无法打开串口");
        }
    }
    
}
