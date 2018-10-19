package com.base.services;

import com.base.entitys.DeviceType;
import com.base.repositorys.DeviceTypeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lx on 2016/12/2.
 */
@Service
public class DeviceTypeService {

    @Autowired
    private DeviceTypeDao deviceTypeDao;

    public DeviceType saveDeviceType(DeviceType deviceType){
        deviceType.setHextypename(IntToHex(deviceType.getTypenum()));
        return deviceTypeDao.save(deviceType);
    }

    public static String IntToHex(int n){
        char[] ch = new char[20];
        int nIndex = 0;
        while ( true ){
            int m = n/16;
            int k = n%16;
            if ( k == 15 )
                ch[nIndex] = 'F';
            else if ( k == 14 )
                ch[nIndex] = 'E';
            else if ( k == 13 )
                ch[nIndex] = 'D';
            else if ( k == 12 )
                ch[nIndex] = 'C';
            else if ( k == 11 )
                ch[nIndex] = 'B';
            else if ( k == 10 )
                ch[nIndex] = 'A';
            else
                ch[nIndex] = (char)('0' + k);
            nIndex++;
            if ( m == 0 )
                break;
            n = m;
        }
        StringBuffer sb = new StringBuffer();
        sb.append(ch, 0, nIndex);
        sb.reverse();
        String strHex = new String("0x");
        strHex += sb.toString();
        return strHex;
    }


}
