package org.hy.common.hart.crc;

import org.hy.common.ByteHelp;





/**
 * CRC算法校验：CRC-32
 * 
 * 校验码长度：4字节（32位）
 * 校验强度高，但计算资源消耗较大
 * 适用于大数据块或高可靠性场景
 * 
 * 典型应用场景
 *   1-文件传输校验（ZIP/RAR压缩包）
 *      压缩文件内存储CRC-32值，解压时验证数据是否损坏。
 *   
 *   2-网络协议（Ethernet帧校验/FTP传输）
 *      Ethernet帧尾的FCS（Frame Check Sequence）使用CRC-32。
 *   
 *   3-磁盘存储系统（ZFS/Btrfs文件系统）
 *      检测硬盘静默错误（Silent Data Corruption）。
 *      
 *   4-数据库备份校验
 *      确保备份文件在传输或存储过程中未发生位翻转。
 *
 * @author      ZhengWei(HY)
 * @createDate  2025-05-07
 * @version     v1.0
 */
public class CRC32 implements ICRC
{
    
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
        // Nothing.
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
        byte[] v_Packet = ByteHelp.substr(i_Packet ,i_BeginIndex ,i_EndIndex - i_BeginIndex);
        
        java.util.zip.CRC32 v_CRC32 = new java.util.zip.CRC32();
        v_CRC32.update(v_Packet);
        long v_CRCValue = v_CRC32.getValue();

        // 转换为4字节的大端序（Big-Endian）
        return new byte[] {
            (byte) ((v_CRCValue >> 24) & 0xFF),
            (byte) ((v_CRCValue >> 16) & 0xFF),
            (byte) ((v_CRCValue >> 8)  & 0xFF),
            (byte) ( v_CRCValue        & 0xFF)
        };
    }


    
    /**
     * 获取：多项式
     */
    public int getPolynomial()
    {
        return 0;
    }


    
    /**
     * 获取：初始值
     */
    public int getInitValue()
    {
        return 0;
    }
    
}
