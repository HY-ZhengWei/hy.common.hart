package org.hy.common.hart.junit;

import org.hy.common.Help;
import org.hy.common.StringHelp;
import org.hy.common.hart.HART;
import org.hy.common.hart.data.HARTConnConfig;
import org.hy.common.hart.enums.DataBit;
import org.hy.common.hart.enums.Parity;
import org.hy.common.hart.enums.StopBit;
import org.hy.common.hart.serialPort.SerialPortFactory;
import org.junit.Test;





/**
 * 测试单元：硬度仪
 *
 * @author      ZhengWei(HY)
 * @createDate  2025-06-21
 * @version     v1.0
 */
public class JU_Hardness
{
    
    @Test
    public void test_getCommPortNames()
    {
        Help.print(SerialPortFactory.getCommPortNames());
    }
    
    
    
    @Test
    public void test_Hardness() throws InterruptedException
    {
        HARTConnConfig v_Config = new HARTConnConfig();
        v_Config.setCommPortName("Silicon Labs CP210x USB to UART Bridge (COM18)");
        v_Config.setBaudRate(2400);
        v_Config.setDataBits(DataBit.DataBit_8);
        v_Config.setStopBit(StopBit.One);
        v_Config.setParityCheck(Parity.None);
        v_Config.setFrequency(0);
        v_Config.setReadTimeout(500);
        
        if ( HART.connect(v_Config) )
        {
            System.out.println("连接设备成功");
        }
        
        for (int x=1; x<=100 * 100; x++)
        {
            String v_Hex = HART.readHex(v_Config.getCommPortName() ,11 ,"QQ");
            System.out.println(v_Hex + " = " + parser(v_Hex));
        }
    }
    
    
    
    @Test
    public void test_parser()
    {
        System.out.println(this.parser("100813140A0A0A00014E100813140A0A0A0000014E"));
    }
    
    
    
    /**
     * 解析硬度值
     * 
     * @author      ZhengWei(HY)
     * @createDate  2025-06-21
     * @version     v1.0
     *
     * @param i_Hex
     * @return
     */
    public Double parser(String i_Hex)
    {
        // 100813140A0A0B0005 01 54 =  -0.5
        // 100813140A0A0B0004 01 53 =  -0.4
        // 100813140A0A0A0000 01 4E =   0.0
        // 100813140A0A0A0005 01 53 =   0.5
        // 100813140A0A0A0307 01 58 =   3.7
        // 100813140A0A010005 01 4A =  10.5
        // 100813140A0A010903 01 51 =  19.3
        // 100813140A0A080700 01 53 =  87.0
        // 100813140A0A090901 01 57 =  99.1
        // 100813140A0A010000 00 44 = 100
        // 100813140A0A010108 00 4D = 118
        if ( Help.isNull(i_Hex) )
        {
            return null;
        }
        
        String v_Key    = "10081314";
        int    v_Len    = 11 * 2;
        int    v_SIndex = i_Hex.indexOf(v_Key);
        int    v_EIndex = i_Hex.indexOf(v_Key ,v_SIndex + 1);
        
        if ( v_SIndex < 0 )
        {
            return null;
        }
        
        if ( v_EIndex < 0 )
        {
            v_EIndex = v_SIndex + v_Len;
        }
        
        if ( v_EIndex > i_Hex.length() )
        {
            return null;
        }
        
        String v_Hex = i_Hex.substring(v_SIndex ,v_EIndex);
        if ( v_Hex.length() != v_Len )
        {
            v_SIndex = i_Hex.indexOf(v_Key ,v_SIndex + 1);
            if ( v_SIndex > 0 )
            {
                return this.parser(i_Hex.substring(v_SIndex));
            }
            else
            {
                return null;
            }
        }
        
        String [] v_Values = StringHelp.splitToArray(v_Hex ,2);
        int       v_Dot    = Integer.parseInt(v_Values[v_Values.length - 2]); // 小数位
        int       v_Sign   = 1;                                               // 正负号 
        String    v_Ret    = "";
        
        for (int x=v_Values.length - 3; x>=0; x--)
        {
            if ( "0A".equals(v_Values[x]) )
            {
                v_Sign = 1;
                break;
            }
            else if ( "0B".equals(v_Values[x]) )
            {
                v_Sign = -1;
                break;
            }
            else
            {
                v_Ret = Integer.parseInt(v_Values[x]) + v_Ret;
            }
        }
        
        return Integer.parseInt(v_Ret) * 1.0D * v_Sign / Math.pow(10 ,v_Dot);
    }
    
}
