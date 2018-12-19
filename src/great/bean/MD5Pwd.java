package great.bean;

import java.security.MessageDigest;

public class MD5Pwd {
	public final static String getMD5(String str) {
		// �������ֽ�ת���� 16 ���Ʊ�ʾ���ַ�
		char hexDiagiArr[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', 'a', 'b', 'c', 'd', 'e', 'f' };
		MessageDigest digest = null;
		try {
			digest = MessageDigest.getInstance("MD5"); // ����MD5�㷨ժҪ
			digest.update(str.getBytes()); // ����ժҪ
			byte mdBytes[] = digest.digest(); // ���ܣ��������ֽ�����
			// �½��ַ����飬����ΪmyBytes�ֽ������2�������ڱ�����ܺ��ֵ
			char newCArr[] = new char[mdBytes.length * 2];
			int k = 0;
			for (int i = 0; i < mdBytes.length; i++) {
				byte byte0 = mdBytes[i];
				newCArr[k++] = hexDiagiArr[byte0 >>> 4 & 0x0f]; // ȡ�ֽ��и� 4 λ������ת��,>>>Ϊ�߼����ƣ�������λһ������
				newCArr[k++] = hexDiagiArr[byte0 & 0x0f]; // ȡ�ֽ��е� 4 λ������ת��
				// ����ַ�0-9�ģ�0-9��ascii��ֵΪ0x30��0x31��0x32 0x33 ...0x39��
				// �����0x0f��λ���ֻ������λ�ϵ��鼴0x0��0x1��������0x9
				// 0000 1010
				// & 0000 1111
				// 0000 1010
			}
			return String.valueOf(newCArr); // ��ת������ַ�ת��Ϊ�ַ���
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
