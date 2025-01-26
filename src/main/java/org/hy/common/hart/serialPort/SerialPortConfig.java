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
    
    /** 通讯频率 */
    private Integer frequency;
    
    /** 通讯超时 */
    private Integer readTimeout;
    
    
    
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
     * 获取：通讯频率
     */
    public Integer getFrequency()
    {
        return frequency;
    }

    
    /**
     * 设置：通讯频率
     * 
     * @param i_Frequency 通讯频率
     */
    public void setFrequency(Integer i_Frequency)
    {
        this.frequency = i_Frequency;
    }

    
    /**
     * 获取：通讯超时
     */
    public Integer getReadTimeout()
    {
        return readTimeout;
    }

    
    /**
     * 设置：通讯超时
     * 
     * @param i_ReadTimeout 通讯超时
     */
    public void setReadTimeout(Integer i_ReadTimeout)
    {
        this.readTimeout = i_ReadTimeout;
    }

}
