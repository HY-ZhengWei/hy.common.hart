package org.hy.common.hart.serialPort;

import java.io.InputStream;
import java.io.OutputStream;

import org.hy.common.xml.log.Logger;

import com.fazecast.jSerialComm.SerialPort;





/**
 * 串口包装类
 *
 * @author      ZhengWei(HY)
 * @createDate  2025-01-17
 * @version     v1.0
 */
public class SerialPortWrapperDefault implements ISerialPortWrapper
{
    private static final Logger $Logger = new Logger(SerialPortWrapperDefault.class);
    
    
    
    /** 串口配置 */
    private SerialPortConfig config;
    
    /** 串口对象 */
    private SerialPort       serialPort;
    
    /** 是否打开连接 */
    private boolean          isOpen;
    
    /** 是否初始化 */
    private boolean          isInit;
    
    
    
    public SerialPortWrapperDefault()
    {
        this.isInit = false;
    }
    
    
    
    public SerialPortWrapperDefault(SerialPortConfig i_Config)
    {
        this();
        this.init(i_Config);
    }
    
    
    
    /**
     * 用配置初始化串口
     * 
     * @author      ZhengWei(HY)
     * @createDate  2025-01-26
     * @version     v1.0
     *
     * @param i_Config
     */
    public synchronized void init(SerialPortConfig i_Config)
    {
        if ( this.isInit )
        {
            return;
        }
        
        SerialPort v_SerialPort = SerialPortFactory.getCommPortByName(i_Config.getCommPortName());
        
        v_SerialPort.setBaudRate(   i_Config.getBaudRate());
        v_SerialPort.setNumDataBits(i_Config.getDataBits().getValue());
        v_SerialPort.setNumStopBits(i_Config.getStopBit().getValue());
        v_SerialPort.setParity(     i_Config.getParityCheck().getValue());
        
        // 配置串口 9600波特率，8数据位，1停止位，无校验
        // v_SerialPort.setComPortParameters(i_ConnectInfo.getBaudRate() ,i_ConnectInfo.getDataBits() ,i_ConnectInfo.getStopBit(), i_ConnectInfo.getParityCheck());  
        // v_SerialPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_BLOCKING, 0, 0);  // TIMEOUT_READ_BLOCKING 阻塞读取模式
        
        this.config     = i_Config;
        this.serialPort = v_SerialPort;
        this.isOpen     = this.serialPort.isOpen();
        this.isInit     = true;
    }
    
    
    /**
     * 获取：是否初始化
     */
    public boolean isInit()
    {
        return isInit;
    }



    /**
     * 反向获取串口配置
     * 
     * @author      ZhengWei(HY)
     * @createDate  2025-01-26
     * @version     v1.0
     *
     * @return
     */
    public SerialPortConfig getConfig()
    {
        return this.config;
    }

    
    /**
     * 波特率
     *
     * @author      ZhengWei(HY)
     * @createDate  2025-01-17
     * @version     v1.0
     *
     * @return
     */
    @Override
    public int getBaudRate()
    {
        return this.serialPort.getBaudRate();
    }

    
    /**
     * 数据位
     *
     * @author      ZhengWei(HY)
     * @createDate  2025-01-17
     * @version     v1.0
     *
     * @return
     */
    @Override
    public int getDataBits()
    {
        return this.serialPort.getNumDataBits();
    }

    
    /**
     * 奇偶校验
     *
     * @author      ZhengWei(HY)
     * @createDate  2025-01-17
     * @version     v1.0
     *
     * @return
     */
    @Override
    public int getParity()
    {
        return this.serialPort.getParity();
    }

    
    /**
     * 停止位
     *
     * @author      ZhengWei(HY)
     * @createDate  2025-01-17
     * @version     v1.0
     *
     * @return
     */
    @Override
    public int getStopBits()
    {
        return this.serialPort.getNumStopBits();
    }
    
    
    /**
     * 打开串口设备的连接
     *
     * @author      ZhengWei(HY)
     * @createDate  2025-01-21
     * @version     v1.0
     *
     * @throws Exception
     */
    @Override
    public void open() throws Exception
    {
        try
        {
            if ( this.serialPort.openPort() )
            {
                this.isOpen = true;
                $Logger.info("打开串口设备成功：" + this.serialPort.getDescriptivePortName());
            }
            else
            {
                throw new RuntimeException("打开串口设备失败：" + this.serialPort.getDescriptivePortName());
            }
        }
        catch(RuntimeException exce)
        {
            $Logger.error(exce.getMessage() ,exce);
            throw exce;
        }
        catch (Exception exce)
        {
            $Logger.error("打开串口设备异常：" + this.serialPort.getDescriptivePortName() ,exce);
            throw exce;
        }
    }
    
    
    /**
     * 关闭串口设备的连接
     *
     * @author      ZhengWei(HY)
     * @createDate  2025-01-21
     * @version     v1.0
     *
     * @throws Exception
     */
    @Override
    public synchronized void close() throws Exception
    {
        try
        {
            if ( this.serialPort.closePort() )
            {
                this.isOpen = false;
                $Logger.info("关闭串口设备成功：" + this.serialPort.getDescriptivePortName());
            }
            else
            {
                $Logger.error("关闭串口设备失败：" + this.serialPort.getDescriptivePortName());
            }
        }
        catch (Exception exce)
        {
            $Logger.error("关闭串口设备异常：" + this.serialPort.getDescriptivePortName() ,exce);
            throw exce;
        }
    }
    
    
    /**
     * 获取：是否打开连接
     */
    public boolean isOpen()
    {
        return isOpen;
    }


    /**
     * 读取串口流数据
     *
     * @author      ZhengWei(HY)
     * @createDate  2025-01-17
     * @version     v1.0
     *
     * @return
     */
    @Override
    public InputStream getInputStream()
    {
        synchronized ( this )
        {
            if ( !this.isOpen )
            {
                return null;
            }
        }
        return this.serialPort.getInputStream();
    }

    
    /**
     * 写入串口流数据 
     *
     * @author      ZhengWei(HY)
     * @createDate  2025-01-21
     * @version     v1.0
     *
     * @return
     */
    @Override
    public OutputStream getOutputStream()
    {
        synchronized ( this )
        {
            if ( !this.isOpen )
            {
                return null;
            }
        }
        return this.serialPort.getOutputStream();
    }
    
    
    /**
     * 读取字节数据
     * 
     * @author      ZhengWei(HY)
     * @createDate  2025-01-24
     * @version     v1.0
     *
     * @param io_Buffer     读取到的数据存放的缓存（输入输出类型）
     * @param i_BufferSize  缓存大小 
     * @return              返回读取到的数据实际大小，小于0时表示异常
     */
    public int readBytes(byte[] io_Buffer ,int i_BufferSize)
    {
        synchronized ( this )
        {
            if ( !this.isOpen )
            {
                return -9;
            }
        }
        
        return this.serialPort.readBytes(io_Buffer ,i_BufferSize);
    }
    
    
    /**
     * 写入字节数据
     * 
     * @author      ZhengWei(HY)
     * @createDate  2025-01-24
     * @version     v1.0
     *
     * @param i_Buffer      写入的数据
     * @param i_BufferSize  数据大小
     * @return              实际写入数据的大小，小于0时表示异常
     */
    public int writeBytes(byte[] i_Buffer ,int i_BufferSize)
    {
        synchronized ( this )
        {
            if ( !this.isOpen )
            {
                return -9;
            }
        }
        
        return this.serialPort.writeBytes(i_Buffer ,i_BufferSize);
    }

}
