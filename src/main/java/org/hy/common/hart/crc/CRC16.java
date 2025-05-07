package org.hy.common.hart.crc;





/**
 * CRC算法校验：CRC-16 (Modbus): 0xA001（低位在前）
 * 
 * 校验码长度：2字节（16位）
 * 平衡速度与可靠性，广泛用于工业协议
 * 支持多项式定制（如Modbus用0x8005，CCITT用0x1021）
 * 
 * 典型应用场景
 *   1-Modbus RTU协议
 *      校验帧完整性（如[设备地址][功能码][数据][CRC16]）。
 *     
 *   2-USB数据包校验
 *      早期USB 1.1/2.0协议使用CRC-16保证控制传输可靠性。
 *      
 *   3-PLC通信（如西门子PPI协议）
 *      工业自动化中PLC与HMI设备的数据交换校验。
 *   
 *   4-GPS模块数据校验（NMEA-0183协议）
 *      验证GPS模块输出的$GPRMC语句完整性。
 *
 * @author      ZhengWei(HY)
 * @createDate  2025-05-07
 * @version     v1.0
 */
public class CRC16 implements ICRC
{
    
    /** 多项式 */
    private int polynomial = 0xA001;   // CRC-16 (Modbus) 多项式 0x8005 的反转（因Modbus低位优先）
    
    /** 初始值 */
    private int initValue  = 0xFFFF;
    
    
    
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
            v_CRC ^= (i_Packet[i] & 0xFF);
            for (int x = 0; x < 8; x++)
            {
                if ( (v_CRC & 1) == 1 )
                {
                    v_CRC = (v_CRC >>> 1) ^ polynomial;
                }
                else
                {
                    v_CRC >>>= 1;
                }
            }
        }
        
        // 转换为2字节数组（低字节在前）
        return new byte[] {
            (byte) (v_CRC & 0xFF),        // 低字节（如 0xAC）
            (byte) ((v_CRC >> 8) & 0xFF)  // 高字节（如 0x40）
        };
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
