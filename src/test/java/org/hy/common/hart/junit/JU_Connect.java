package org.hy.common.hart.junit;





public class JU_Connect
{
    
    public void test()
    {
     // 设置数据监听器以接收来自 HART7 设备的数据
        /*
        $SerialPort.addDataListener(new SerialPortDataListener() 
        {
            @Override
            public int getListeningEvents()
            {
                return SerialPort.LISTENING_EVENT_DATA_RECEIVED;
            }

            @Override
            public void serialEvent(SerialPortEvent event)
            {
                try
                {
                    byte [] v_ReadData = event.getReceivedData();
                    String v_ReadValue = StringHelp.bytesToHex(v_ReadData);
                    System.out.println("Received data: " + v_ReadValue);
                }
                catch (Exception exce)
                {
                    exce.printStackTrace();
                }
            }
        });
        
        // 设置数据监听器
        $SerialPort.addDataListener(new SerialPortDataListener() 
        {
            @Override
            public int getListeningEvents()
            {
                return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
            }

            @Override
            public void serialEvent(SerialPortEvent event)
            {
                try
                {
                    byte [] readBuffer = new byte[$SerialPort.bytesAvailable()];
                    int numRead = $SerialPort.readBytes(readBuffer ,readBuffer.length);
                    System.out.println("Read Num：" + numRead);
                    String v_ReadValue = StringHelp.bytesToHex(readBuffer ,0 ,numRead);
                    System.out.println("Read：" + v_ReadValue);
                }
                catch (Exception exce)
                {
                    exce.printStackTrace();
                }
            }
        });
        */
    }
    
}
