
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
	
	/** 将question转化为后缀表达式并求值   **/
	static public double calc(String question){
		System.out.println("calc: " + question);
		
		char[] ch = new char[50];
		char[] oc = new char[15]; 
		double[] num = new double[15];
		boolean have_num = false, pm = false;
		int pn = -1, pc = -1;  //tmp用于存放中间数值，pn用于在num数组中模拟栈顶， pc用于在ch数组中模拟栈顶
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
				
				if(i == ch.length - 1) num[++pn] = (pm ? -tmp : tmp);   //最后一个数入栈
			}
			else {
				if(have_num) num[++pn] = pm == true ? -tmp : tmp;       //tmp入栈
				tmp = 0; flg = 1;
				have_num = false;
				pm = false;
				
				if(ch[i] == '(') oc[++pc] = ch[i];   //特判左括号直接入栈
				else if(ch[i] == ')') {  //特判右括号两括号间内容计算
					while(oc[pc] != '(') {
						double num1 = num[pn--];
						double num2 = num[pn--];
						char ch1 = oc[pc--];
						num[++pn] = calced(num2, num1, ch1);
					}
					pc--; // '('出栈
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
