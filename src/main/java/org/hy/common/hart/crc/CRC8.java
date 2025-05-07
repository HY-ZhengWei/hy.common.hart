package org.hy.common.hart.crc;





/**
 * CRC算法校验：CRC-8
 * 
 * 校验码长度：1字节（8位）
 * 计算速度快，资源占用低
 * 适用于短数据帧和简单校验
 * 
 * 典型应用场景
 *   1-Wire总线通信（如DS18B20温度传感器）
 *      用于校验传感器返回的短数据帧（如温度值）。
 *      
 *   2-RFID标签数据校验
 *      验证低频RFID标签存储的ID数据完整性。
 *      
 *   3-工业控制简单指令校验
 *      如PLC发送的开关量控制指令（0x01开/0x00关）的快速校验。
 *   
 *   4-EEPROM存储校验
 *      检查小型存储设备（如AT24C02）的配置参数是否损坏。
 *
 * @author      ZhengWei(HY)
 * @createDate  2025-05-07
 * @version     v1.0
 */
public class CRC8 implements ICRC
{
    
    /** 多项式 */
    private int polynomial = 0x07;     // CRC-8多项式（常用值：0x07, 0x9B）
    
    /** 初始值 */
    private int initValue  = 0x00;
    
    
    
    /**
     * 初始化多项式及初始值（可选配置）
     * 
     * @author      ZhengWei(HY)
     * @createDate  2025-05-07
     * @version     v1.0
     *
     * @param i_Polynomial  多项式的值
     * @param i_InitValue   初始化的值
     */
    public void initPolynomial(int i_Polynomial ,int i_InitValue)
    {
        this.polynomial = i_Polynomial;
        this.initValue  = i_InitValue;
    }
    
    
    
    /**
     * 计算CRC算法的校验位
     * 
     * @author      ZhengWei(HY)
     * @createDate  2025-05-07
     * @version     v1.0
     *
     * @param i_Packet      数据报文
     * @param i_BeginIndex  数据报文的开始位置，包含开始位置，下标从0开始。
     * @param i_EndIndex    数据报文的结束位置，小于结束位置。
     * @return              返回校验位
     */
    public byte[] calcCRC(byte[] i_Packet ,int i_BeginIndex ,int i_EndIndex)
    {
        int v_CRC = initValue;
        for (int i=i_BeginIndex; i<i_EndIndex; i++)
        {
            v_CRC ^= (i_Packet[i] & 0xFF); // 转换为无符号byte
            for (int x = 0; x < 8; x++)
            {
                if ( (v_CRC & 0x80) != 0 )
                {
                    v_CRC = (v_CRC << 1) ^ polynomial;
                }
                else
                {
                    v_CRC <<= 1;
                }
            }
            v_CRC &= 0xFF; // 保留8位
        }
        
        return new byte[] { (byte) v_CRC };
    }


    
    /**
     * 获取：多项式
     */
    public int getPolynomial()
    {
        return polynomial;
    }


    
    /**
     * 获取：初始值
     */
    public int getInitValue()
    {
        return initValue;
    }
    
}
