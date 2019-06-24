
public class Lib {
	
	static private int getw(char c){
		if(c=='*' || c=='/') return 2;
		else return 1;
	}
	
	static private double calced(double a, double b, char c) {
		
		if(c == '+') return a + b;
		else if(c == '-') return a - b;
		else if(c == '*') return a * b;
		else return a/b;
	}
	
	/** ��questionת��Ϊ��׺���ʽ����ֵ   **/
	static public double calc(String question){
		System.out.println("calc: " + question);
		
		char[] ch = new char[50];
		char[] oc = new char[15]; 
		double[] num = new double[15];
		boolean have_num = false, pm = false;
		int pn = -1, pc = -1;  //tmp���ڴ���м���ֵ��pn������num������ģ��ջ���� pc������ch������ģ��ջ��
		double tmp = 0, flg = 1;
		
		ch = question.toCharArray();
		
		
		for(int i=0; i<ch.length; i++){
//			System.out.println(i+ ":" + ch[i]);
			if(!have_num && tmp == 0 && ch[i] == '-') {
				System.out.println(i + " ** " + pm);
				pm = true;
			}
			else if(Character.isDigit(ch[i]) || ch[i] == '.') {
				if(ch[i] == '.') {
					flg *= 0.1;
					continue;
				}
				
				if(flg == 1.0) tmp = tmp*10 + ch[i]-'0'; 
				else {
					tmp = tmp + (ch[i]-'0')*flg;
					flg *= 0.1;
				}
				
				have_num = true;
				
				if(i == ch.length - 1) num[++pn] = (pm ? -tmp : tmp);   //���һ������ջ
			}
			else {
				if(have_num) num[++pn] = pm == true ? -tmp : tmp;       //tmp��ջ
				tmp = 0; flg = 1;
				have_num = false;
				pm = false;
				
				if(ch[i] == '(') oc[++pc] = ch[i];   //����������ֱ����ջ
				else if(ch[i] == ')') {  //���������������ż����ݼ���
					while(oc[pc] != '(') {
						double num1 = num[pn--];
						double num2 = num[pn--];
						char ch1 = oc[pc--];
						num[++pn] = calced(num2, num1, ch1);
					}
					pc--; // '('��ջ
				}
				else {
					while(pc!=-1 && oc[pc]!='(' && getw(oc[pc]) >= getw(ch[i]) ) {
						double num1 = num[pn--];
						double num2 = num[pn--];
						char ch1 = oc[pc--];
						num[++pn] = calced(num2, num1, ch1);
					}
					oc[++pc] = ch[i];
				}
			}
		}

		while(pc != -1) {
			double num1 = num[pn--];
			double num2 = num[pn--];
			char ch1 = oc[pc--];
			num[++pn] = calced(num2, num1, ch1);
		}
		
		
		return num[0];
	}	
}
