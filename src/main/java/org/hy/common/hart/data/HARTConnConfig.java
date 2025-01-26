package org.hy.common.hart.data;

import org.hy.common.hart.serialPort.SerialPortConfig;





/**
 * 数据层：HART连接信息
 *
 * @author      ZhengWei(HY)
 * @createDate  2024-12-30
 * @version     v1.0
 */
public class HARTConnConfig extends SerialPortConfig
{
   
    private static final long serialVersionUID = -5598568976409702544L;
    
    
    
    /** 设备通讯地址 */
    private Integer deviceAddress;

    
    
    /**
     * 获取：设备通讯地址
     */
    public Integer getDeviceAddress()
    {
        return deviceAddress;
    }


    /**
     * 设置：设备通讯地址
     * 
     * @param i_DeviceAddress 设备通讯地址
     */
    public void setDeviceAddress(Integer i_DeviceAddress)
    {
        this.deviceAddress = i_DeviceAddress;
    }
    
}
