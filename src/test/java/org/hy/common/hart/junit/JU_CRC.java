package org.hy.common.hart.junit;

import java.util.HexFormat;

import org.hy.common.hart.crc.CRC;
import org.hy.common.hart.crc.CRCType;
import org.junit.Test;





/**
 * 测试单元：CRC算法校验
 *
 * @author      ZhengWei(HY)
 * @createDate  2025-05-07
 * @version     v1.0
 */
public class JU_CRC
{
    
    @Test
    public void test_CRC8()
    {
        // 输入数据（不包含CRC部分）
        // 数据            CRC验证码
        // 0106000A0FA0   4F
        byte [] v_Packet = HexFormat.of().parseHex("0106000A0FA0");
        byte [] v_CRC    = CRC.getCRC(CRCType.CRC8).calcCRC(v_Packet ,0 ,v_Packet.length);
        
        // 输出低字节在前的结果
        System.out.printf("CRC8: %02X", v_CRC[0]); 
    }
    
    
    
    @Test
    public void test_CRC16()
    {
        // 输入数据（不包含CRC部分）
        // 数据            CRC验证码
        // 0106000A0FA0   AC40
        // 0106000A1F40   A008
        // 0106000A3A98   BAC2
        // 0106000A4E20   9DB0
        byte [] v_Packet = HexFormat.of().parseHex("0106000A0FA0");
        byte [] v_CRC    = CRC.getCRC(CRCType.CRC16).calcCRC(v_Packet ,0 ,v_Packet.length);
        
        // 输出低字节在前的结果
        System.out.printf("CRC16: %02X %02X", v_CRC[0], v_CRC[1]); 
    }
    
    
    
    @Test
    public void test_CRC32()
    {
        // 输入数据（不包含CRC部分）
        // 数据            CRC验证码
        // 0106000A0FA0   A907BD57
        byte [] v_Packet = HexFormat.of().parseHex("0106000A0FA0");
        byte [] v_CRC    = CRC.getCRC(CRCType.CRC32).calcCRC(v_Packet ,0 ,v_Packet.length);
        
        // 输出低字节在前的结果
        System.out.printf("CRC32: %02X %02X %02X %02X", v_CRC[0], v_CRC[1], v_CRC[2] , v_CRC[3]); 
    }
    
}
