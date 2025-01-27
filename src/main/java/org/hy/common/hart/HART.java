package org.hy.common.hart;

import org.hy.common.ByteHelp;
import org.hy.common.Help;
import org.hy.common.StringHelp;
import org.hy.common.hart.data.HARTConnConfig;
import org.hy.common.hart.enums.DataBit;
import org.hy.common.hart.enums.Parity;
import org.hy.common.hart.enums.StopBit;
import org.hy.common.hart.serialPort.ISerialPortWrapper;
import org.hy.common.hart.serialPort.SerialPortFactory;
import org.hy.common.xml.log.Logger;

import com.fazecast.jSerialComm.SerialPort;





/**
 * HART协议的操作类
 *
 * @author      ZhengWei(HY)
 * @createDate  2024-12-30
 * @version     v1.0
 */
public class HART
{
    
    private static final Logger $Logger = new Logger(HART.class);
    
    
    
    /**
     * 串口是否连接成功
     * 
     * @author      ZhengWei(HY)
     * @createDate  2024-12-30
     * @version     v1.0
     *
     * @param i_CommPortName  串口名称
     * @return
     */
    public static boolean isConnect(String i_CommPortName)
    {
        if ( !Help.isNull(i_CommPortName) )
        {
            ISerialPortWrapper v_SerialPort = SerialPortFactory.get(i_CommPortName);
            if ( v_SerialPort == null )
            {
                return false;
            }
            else
            {
                return v_SerialPort.isOpen();
            }
        }
        else
        {
            return false;
        }
    }
    
    
    
    /**
     * 关闭串口连接
     * 
     * @author      ZhengWei(HY)
     * @createDate  2024-12-30
     * @version     v1.0
     *
     * @param i_ConnectInfo  串口名称
     * @return
     */
    public static synchronized boolean close(String i_CommPortName)
    {
        ISerialPortWrapper v_SerialPort = SerialPortFactory.get(i_CommPortName);
        if ( v_SerialPort == null )
        {
            return false;
        }
        else
        {
            if ( v_SerialPort.isOpen() )
            {
                try
                {
                    v_SerialPort.close();
                    return true;
                }
                catch (Exception exce)
                {
                    $Logger.error(exce);
                    return false;
                }
            }
            else
            {
                return true;
            }
        }
    }
    
    
    
    /**
     * 连接串口
     * 
     * @author      ZhengWei(HY)
     * @createDate  2024-12-30
     * @version     v1.0
     * 
     * @param i_ConnectInfo  连接信息
     * @return
     */
    public static synchronized boolean connect(HARTConnConfig i_ConnectInfo)
    {
        ISerialPortWrapper v_SerialPort = SerialPortFactory.get(i_ConnectInfo);
        if ( v_SerialPort == null )
        {
            return false;
        }
        
        if ( v_SerialPort.isOpen() )
        {
            close(i_ConnectInfo.getCommPortName());
        }
        
        try
        {
            v_SerialPort.open();
            return true;
        }
        catch (Exception exce)
        {
            $Logger.error(exce);
            return false;
        }
    }
    
    
    
    /**
     * 连接串口
     * 
     * @author      ZhengWei(HY)
     * @createDate  2025-01-27
     * @version     v1.0
     * 
     * @param i_ConnectInfo  连接信息
     * @param i_Wrapper      指定串口的包装类
     * @return
     */
    public static synchronized <E extends ISerialPortWrapper> boolean connect(HARTConnConfig i_ConnectInfo ,Class<E> i_Wrapper)
    {
        ISerialPortWrapper v_SerialPort = SerialPortFactory.get(i_ConnectInfo ,i_Wrapper);
        if ( v_SerialPort == null )
        {
            return false;
        }
        
        if ( v_SerialPort.isOpen() )
        {
            close(i_ConnectInfo.getCommPortName());
        }
        
        try
        {
            v_SerialPort.open();
            return true;
        }
        catch (Exception exce)
        {
            $Logger.error(exce);
            return false;
        }
    }
    
    
    
    /**
     * 按串口名称匹配到串口对象的信息
     * 
     * @author      ZhengWei(HY)
     * @createDate  2024-12-31
     * @version     v1.0
     * 
     * @param i_CommPortName  串口名称
     * @return
     */
    public static HARTConnConfig getDeviceInfo(String i_CommPortName)
    {
        HARTConnConfig     v_Ret     = null;
        ISerialPortWrapper v_Wrapper = SerialPortFactory.get(i_CommPortName);
        
        if ( v_Wrapper != null )
        {
            v_Ret = (HARTConnConfig) v_Wrapper.getConfig();
        }
        else
        {
            SerialPort v_SerialPort = SerialPortFactory.getCommPortByName(i_CommPortName);
            
            if ( v_SerialPort == null )
            {
                return v_Ret;
            }
            
            v_Ret = new HARTConnConfig();
            v_Ret.setBaudRate(   v_SerialPort.getBaudRate());
            v_Ret.setDataBits(   DataBit.get(v_SerialPort.getNumDataBits()));
            v_Ret.setStopBit(    StopBit.get(v_SerialPort.getNumStopBits()));
            v_Ret.setParityCheck(Parity.get(v_SerialPort.getParity()));
        }
        
        return v_Ret;
    }
    
    
    
    /**
     * 发送数据报文
     * 
     * @author      ZhengWei(HY)
     * @createDate  2024-12-31
     * @version     v1.0
     * 
     * @param i_CommPortName  串口名称
     * @param i_Packet        数据报文
     * @return                返回报文
     */
    public static byte[] send(String i_CommPortName ,byte[] i_Packet)
    {
        return send(i_CommPortName ,i_Packet ,i_Packet.length ,null);
    }
    
    
    
    /**
     * 发送数据报文
     * 
     * @author      ZhengWei(HY)
     * @createDate  2024-12-31
     * @version     v1.0
     * 
     * @param i_ConnectInfo   连接信息
     * @param i_Packet        数据报文
     * @param i_PacketLength  数据报文的有效长度
     * @return                返回报文。设备不存在时返回长度为0
     */
    public static byte[] send(String i_CommPortName ,byte[] i_Packet ,int i_PacketLength)
    {
        return send(i_CommPortName ,i_Packet ,i_PacketLength ,null);
    }
    
    
    
    /**
     * 发送数据报文
     * 
     * @author      ZhengWei(HY)
     * @createDate  2024-12-31
     * @version     v1.0
     * 
     * @param i_CommPortName  串口名称
     * @param i_Packet        数据报文
     * @param i_PacketLength  数据报文的有效长度
     * @param i_Comment       动作注释说明
     * @return                返回报文。设备不存在时返回长度为0
     */
    public static byte[] send(String i_CommPortName ,byte[] i_Packet ,int i_PacketLength ,String i_Comment)
    {
        // 发送数据包
        $Logger.info(Help.NVL(i_Comment) + ".发送报文：" + StringHelp.bytesToHex(i_Packet ,0 ,i_PacketLength));
        
        ISerialPortWrapper v_SerialPort = SerialPortFactory.get(i_CommPortName);
        if ( v_SerialPort == null )
        {
            return new byte[0];
        }
        
        if ( !v_SerialPort.isOpen() )
        {
            return new byte[0];
        }
        
        int v_Ret = v_SerialPort.writeBytes(i_Packet, i_PacketLength);
        if ( v_Ret <= 0  )
        {
            $Logger.info(Help.NVL(i_Comment) + ".发送异常");
        }
        
        byte[] v_Buffer       = new byte[1024];
        int    v_BufferLength = 0;
        long    v_TryCount    = 0;
        
        do 
        {
            try
            {
                Thread.sleep(v_SerialPort.getConfig().getFrequency());
            }
            catch (Exception exce)
            {
                // Nothing.
            }
            
            v_BufferLength = v_SerialPort.readBytes(v_Buffer, v_Buffer.length);
            v_TryCount += v_SerialPort.getConfig().getFrequency();
        }
        while ( v_TryCount < v_SerialPort.getConfig().getReadTimeout() && v_BufferLength <= 0 );
        
        if ( v_TryCount >= v_SerialPort.getConfig().getReadTimeout() && v_BufferLength <= 0 )
        {
            $Logger.info(Help.NVL(i_Comment) + ".接收报文：超时");
        }
        else
        {
            String v_ReadValue = StringHelp.bytesToHex(v_Buffer ,0 ,v_BufferLength);
            $Logger.info(Help.NVL(i_Comment) + ".接收报文：" + v_ReadValue);
        }
        
        return ByteHelp.substr(v_Buffer ,0 ,v_BufferLength);
    }
    
    
    
    /**
     * 发送数据报文（返回十六进制的报文）
     * 
     * @author      ZhengWei(HY)
     * @createDate  2025-01-14
     * @version     v1.0
     * 
     * @param i_CommPortName  串口名称
     * @param i_Packet        数据报文
     * @param i_PacketLength  数据报文的有效长度
     * @param i_Comment       动作注释说明
     * @return                返回报文。超时时返回空字符串
     */
    public static String sendHex(String i_CommPortName ,byte[] i_Packet ,int i_PacketLength ,String i_Comment)
    {
        $Logger.info(Help.NVL(i_Comment) + ".发送报文：" + StringHelp.bytesToHex(i_Packet ,0 ,i_PacketLength));
        
        ISerialPortWrapper v_SerialPort = SerialPortFactory.get(i_CommPortName);
        if ( v_SerialPort == null )
        {
            return "";
        }
        
        if ( !v_SerialPort.isOpen() )
        {
            return "";
        }
        
        // 发送数据包
        
        int v_Ret = v_SerialPort.writeBytes(i_Packet, i_PacketLength);
        if ( v_Ret <= 0  )
        {
            $Logger.info(Help.NVL(i_Comment) + ".发送异常");
        }
        
        byte[] v_Buffer       = new byte[1024];
        int    v_BufferLength = 0;
        long    v_TryCount    = 0;
        
        do 
        {
            try
            {
                Thread.sleep(v_SerialPort.getConfig().getFrequency());
            }
            catch (Exception exce)
            {
                // Nothing.
            }
            
            v_BufferLength = v_SerialPort.readBytes(v_Buffer, v_Buffer.length);
            v_TryCount += v_SerialPort.getConfig().getFrequency();
        }
        while ( v_TryCount < v_SerialPort.getConfig().getReadTimeout() && v_BufferLength <= 0 );
        
        if ( v_TryCount >= v_SerialPort.getConfig().getReadTimeout() && v_BufferLength <= 0 )
        {
            $Logger.info(Help.NVL(i_Comment) + ".接收报文：超时");
            return "";
        }
        else
        {
            String v_ReadValue = StringHelp.bytesToHex(v_Buffer ,0 ,v_BufferLength);
            $Logger.info(Help.NVL(i_Comment) + ".接收报文：" + v_ReadValue);
            return v_ReadValue;
        }
    }
    
    
    
    /**
     * 计算校验位
     * 
     * @author      ZhengWei(HY)
     * @createDate  2025-01-03
     * @version     v1.0
     * 
     * @param i_Packet      数据报文
     * @param i_BeginIndex  数据报文的开始位置，包含开始位置，下标从0开始。
     * @param i_BeginIndex  数据报文的结束位置，小于结束位置。
     * @return              返回校验位
     */
    public static byte calcCV(byte[] i_Packet ,int i_BeginIndex ,int i_EndIndex)
    {
        byte v_CV = 0;
        for (int i=i_BeginIndex; i<i_EndIndex; i++)
        {
            v_CV = (byte) (v_CV ^ i_Packet[i]);
        }
        return v_CV;
    }
    
}
