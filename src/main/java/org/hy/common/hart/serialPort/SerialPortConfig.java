package org.hy.common.hart.serialPort;

import java.io.Serializable;

import org.hy.common.hart.enums.DataBit;
import org.hy.common.hart.enums.Parity;
import org.hy.common.hart.enums.StopBit;





/**
 * 数据层：HART连接信息
 *
 * @author      ZhengWei(HY)
 * @createDate  2024-12-30
 * @version     v1.0
 *              v2.0  2025-09-28  添加：RTS引脚、DTR引脚、TxD线强制低电平
 */
public class SerialPortConfig implements Serializable
{
   
    private static final long serialVersionUID = -2050595047657636133L;
    
    

    /** 通讯串口名称 */
    private String  commPortName;
    
    /** 波特率 */
    private Integer baudRate;
    
    /** 数据位 */
    private DataBit dataBits;
    
    /** 停止位 */
    private StopBit stopBit;
    
    /** 奇偶校验 */
    private Parity  parityCheck;
    
    /** 通讯频率（单位：毫秒） */
    private Integer frequency;
    
    /** 通讯超时（单位：毫秒） */
    private Integer readTimeout;
    
    /** 写超时（单位：毫秒） */
    private Integer writeTimeout;
    
    /** 超时阻塞模式 */
    private Integer timeoutModes;
    
    /** 软硬流控制 */
    private Integer flowControls;
    
    /** RTS 引脚。用于硬件流控（RTS/CTS 流控），即 DTE 通过 RTS 通知 DCE（如调制解调器）它已准备好接收数据 */
    private Boolean rts;
    
    /** DTR 引脚。用于唤醒或复位设备（如某些单片机通过 DTR 信号触发复位） */
    private Boolean dtr;
    
    /** TxD 线强制低电平。Break信号被用作特殊的同步或复位信号 */
    private Boolean BREAK;
    
    
    
    /**
     * 获取：通讯串口名称
     */
    public String getCommPortName()
    {
        return commPortName;
    }

    
    /**
     * 设置：通讯串口名称
     * 
     * @param i_CommPortName 通讯串口名称
     */
    public void setCommPortName(String i_CommPortName)
    {
        this.commPortName = i_CommPortName;
    }

    
    /**
     * 获取：波特率
     */
    public Integer getBaudRate()
    {
        return baudRate;
    }

    
    /**
     * 设置：波特率
     * 
     * @param i_BaudRate 波特率
     */
    public void setBaudRate(Integer i_BaudRate)
    {
        this.baudRate = i_BaudRate;
    }

    
    /**
     * 获取：数据位
     */
    public DataBit getDataBits()
    {
        return dataBits;
    }

    
    /**
     * 设置：数据位
     * 
     * @param i_DataBits 数据位
     */
    public void setDataBits(DataBit i_DataBits)
    {
        this.dataBits = i_DataBits;
    }

    
    /**
     * 获取：停止位
     */
    public StopBit getStopBit()
    {
        return stopBit;
    }

    
    /**
     * 设置：停止位
     * 
     * @param i_StopBit 停止位
     */
    public void setStopBit(StopBit i_StopBit)
    {
        this.stopBit = i_StopBit;
    }

    
    /**
     * 获取：奇偶校验
     */
    public Parity getParityCheck()
    {
        return parityCheck;
    }

    
    /**
     * 设置：奇偶校验
     * 
     * @param i_ParityCheck 奇偶校验
     */
    public void setParityCheck(Parity i_ParityCheck)
    {
        this.parityCheck = i_ParityCheck;
    }

    
    /**
     * 获取：通讯频率（单位：毫秒）
     */
    public Integer getFrequency()
    {
        return frequency;
    }

    
    /**
     * 设置：通讯频率（单位：毫秒）
     * 
     * @param i_Frequency 通讯频率（单位：毫秒）
     */
    public void setFrequency(Integer i_Frequency)
    {
        this.frequency = i_Frequency;
    }

    
    /**
     * 获取：通讯超时（单位：毫秒）
     */
    public Integer getReadTimeout()
    {
        return readTimeout;
    }

    
    /**
     * 设置：通讯超时（单位：毫秒）
     * 
     * @param i_ReadTimeout 通讯超时（单位：毫秒）
     */
    public void setReadTimeout(Integer i_ReadTimeout)
    {
        this.readTimeout = i_ReadTimeout;
    }

    
    /**
     * 获取：写超时（单位：毫秒）
     */
    public Integer getWriteTimeout()
    {
        return writeTimeout;
    }

    
    /**
     * 设置：写超时（单位：毫秒）
     * 
     * @param i_WriteTimeout 写超时（单位：毫秒）
     */
    public void setWriteTimeout(Integer i_WriteTimeout)
    {
        this.writeTimeout = i_WriteTimeout;
    }


    /**
     * 获取：软硬流控制
     */
    public Integer getFlowControls()
    {
        return flowControls;
    }

    
    /**
     * 设置：软硬流控制
     * 
     * @param i_FlowControls 软硬流控制
     */
    public void setFlowControls(Integer i_FlowControls)
    {
        this.flowControls = i_FlowControls;
    }

    
    /**
     * 获取：超时阻塞模式
     */
    public Integer getTimeoutModes()
    {
        return timeoutModes;
    }

    
    /**
     * 设置：超时阻塞模式
     * 
     * @param i_TimeoutModes 超时阻塞模式
     */
    public void setTimeoutModes(Integer i_TimeoutModes)
    {
        this.timeoutModes = i_TimeoutModes;
    }

    
    /**
     * 获取：RTS 引脚。用于硬件流控（RTS/CTS 流控），即 DTE 通过 RTS 通知 DCE（如调制解调器）它已准备好接收数据
     */
    public Boolean getRTS()
    {
        return rts;
    }

    
    /**
     * 设置：RTS 引脚。用于硬件流控（RTS/CTS 流控），即 DTE 通过 RTS 通知 DCE（如调制解调器）它已准备好接收数据
     * 
     * @param i_Rts RTS 引脚。用于硬件流控（RTS/CTS 流控），即 DTE 通过 RTS 通知 DCE（如调制解调器）它已准备好接收数据
     */
    public void setRTS(Boolean i_Rts)
    {
        this.rts = i_Rts;
    }

    
    /**
     * 获取：DTR 引脚。用于唤醒或复位设备（如某些单片机通过 DTR 信号触发复位）
     */
    public Boolean getDTR()
    {
        return dtr;
    }

    
    /**
     * 设置：DTR 引脚。用于唤醒或复位设备（如某些单片机通过 DTR 信号触发复位）
     * 
     * @param i_Dtr DTR 引脚。用于唤醒或复位设备（如某些单片机通过 DTR 信号触发复位）
     */
    public void setDTR(Boolean i_Dtr)
    {
        this.dtr = i_Dtr;
    }

    
    /**
     * 获取：TxD 线强制低电平。Break信号被用作特殊的同步或复位信号
     */
    public Boolean getBREAK()
    {
        return BREAK;
    }

    
    /**
     * 设置：TxD 线强制低电平。Break信号被用作特殊的同步或复位信号
     * 
     * @param i_BREAK TxD 线强制低电平。Break信号被用作特殊的同步或复位信号
     */
    public void setBREAK(Boolean i_BREAK)
    {
        this.BREAK = i_BREAK;
    }

}
