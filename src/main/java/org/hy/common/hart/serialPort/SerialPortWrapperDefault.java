package org.hy.common.hart.serialPort;

import java.io.InputStream;
import java.io.OutputStream;

import org.hy.common.Help;
import org.hy.common.hart.enums.DataBit;
import org.hy.common.hart.enums.FlowControl;
import org.hy.common.hart.enums.Parity;
import org.hy.common.hart.enums.StopBit;
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
        
        v_SerialPort.setBaudRate(i_Config.getBaudRate());
        if ( Help.isNull(i_Config.getDataBits()) )
        {
            v_SerialPort.setNumDataBits(DataBit.DataBit_8.getValue());
        }
        else
        {
            v_SerialPort.setNumDataBits(i_Config.getDataBits().getValue());
        }
        
        if ( Help.isNull(i_Config.getStopBit()) )
        {
            v_SerialPort.setNumStopBits(StopBit.One.getValue());
        }
        else
        {
            v_SerialPort.setNumStopBits(i_Config.getStopBit().getValue());
        }
        
        if ( Help.isNull(i_Config.getParityCheck()) )
        {
            v_SerialPort.setParity(Parity.None.getValue());
        }
        else
        {
            v_SerialPort.setParity(i_Config.getParityCheck().getValue());
        }
        
        if ( Help.isNull(i_Config.getFlowControls()) )
        {
            v_SerialPort.setFlowControl(FlowControl.Disabled.getValue());
        }
        else
        {
            v_SerialPort.setFlowControl(i_Config.getFlowControls());
        }
        
        if ( !Help.isNull(i_Config.getBREAK()) )
        {
            if ( i_Config.getBREAK() )
            {
                v_SerialPort.setBreak();
            }
            else
            {
                v_SerialPort.clearBreak();
            }
        }
        
        if ( !Help.isNull(i_Config.getRTS()) )
        {
            if ( i_Config.getRTS() )
            {
                v_SerialPort.setRTS();
            }
            else
            {
                v_SerialPort.clearRTS();
            }
        }
        
        if ( !Help.isNull(i_Config.getDTR()) )
        {
            if ( i_Config.getDTR() )
            {
                v_SerialPort.setDTR();
            }
            else
            {
                v_SerialPort.clearDTR();
            }
        }
        
        if ( !Help.isNull(i_Config.getTimeoutModes()) )
        {
            v_SerialPort.setComPortTimeouts(i_Config.getTimeoutModes() ,i_Config.getReadTimeout() ,i_Config.getWriteTimeout());
        }
        
        // 配置串口 9600波特率，8数据位，1停止位，无校验
        // v_SerialPort.setComPortParameters(i_ConnectInfo.getBaudRate() ,i_ConnectInfo.getDataBits() ,i_ConnectInfo.getStopBit(), i_ConnectInfo.getParityCheck());  
        
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
     * 获取：软硬流控制
     */
    @Override
    public int getFlowControls()
    {
        return this.serialPort.getFlowControlSettings();
    }
    
    
    
    /**
     * 获取：超时阻塞中的读超时（单位：毫秒）
     */
    @Override
    public int getReadTimeout()
    {
        return this.serialPort.getReadTimeout();
    }
    
    
    /**
     * 获取：超时阻塞中的写超时（单位：毫秒）
     */
    @Override
    public int getWriteTimeout()
    {
        return this.serialPort.getWriteTimeout();
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
    public synchronized void open() throws Exception
    {
        try
        {
            if ( this.serialPort.openPort() )
            {
                this.isOpen = true;
                $Logger.info("打开串口设备成功：" + this.config.getCommPortName());
            }
            else
            {
                throw new RuntimeException("打开串口设备失败：" + this.config.getCommPortName());
            }
        }
        catch(RuntimeException exce)
        {
            $Logger.error(exce.getMessage() ,exce);
            throw exce;
        }
        catch (Exception exce)
        {
            $Logger.error("打开串口设备异常：" + this.config.getCommPortName() ,exce);
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
                $Logger.info("关闭串口设备成功：" + this.config.getCommPortName());
            }
            else
            {
                $Logger.error("关闭串口设备失败：" + this.config.getCommPortName());
            }
        }
        catch (Exception exce)
        {
            $Logger.error("关闭串口设备异常：" + this.config.getCommPortName() ,exce);
            throw exce;
        }
    }
    
    
    /**
     * 获取：是否打开连接
     */
    public synchronized boolean isOpen()
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
