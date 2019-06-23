import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main extends Frame implements ActionListener,  WindowListener {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField questionField;       //算式显示区
    private JTextField displayField;        //计算结果显示区
    private Dialog dialog;
    private JButton button_sqt, button_pm, button_BS, button_C, button_CE;
    private JButton button_1, button_2, button_3, button_4, button_5;
    private JButton button_6, button_7, button_8, button_9, button_0;
    private JButton button_pls, button_sub, button_mul, button_div;
    private JButton button_log, button_tan, button_cos, button_sin, button_exp;
    private JButton button_pot, button_eql, button_abs, button_int;
    private JButton button_lft, button_rgt;
    private String question;
    private String lastCommand;       	    //保存+, -, *, /, =命令0
    private double ans;           	  	  //保存计算结果
    private boolean start = true, flg = false;         //判断是否为数字的开始
    private int cnt;
   
    public Main() {      //构造方法设置布局、为按钮注册事件监听器

        super("My Calculator");
        this.setLocation(350, 150);
        this.setSize(653, 568);
        this.setResizable(true);
        this.setLayout(new GridLayout(7, 1));
        
        questionField = new JTextField(30);
        questionField.setBackground(SystemColor.activeCaption);
        questionField.setFont(new Font("黑体",  Font.BOLD,  30));
        questionField.setHorizontalAlignment(JTextField.RIGHT);//右对齐
        this.add(questionField);
        questionField.setEditable(true);
        
        displayField = new JTextField(30);
        displayField.setBackground(SystemColor.activeCaption);
        displayField.setFont(new Font("黑体",  Font.BOLD,  30));
        this.add(displayField);
        displayField.setEditable(true);      
        
        button_1 = new JButton("1"); button_1.setBackground(Color.ORANGE); button_1.setFont(new Font("楷体", Font.BOLD, 26));
        button_2 = new JButton("2"); button_2.setBackground(Color.ORANGE); button_2.setFont(new Font("楷体", Font.BOLD, 26));
        button_3 = new JButton("3"); button_3.setBackground(Color.ORANGE); button_3.setFont(new Font("楷体", Font.BOLD, 26));
        button_4 = new JButton("4"); button_4.setBackground(Color.ORANGE); button_4.setFont(new Font("楷体", Font.BOLD, 26));
        button_5 = new JButton("5"); button_5.setBackground(Color.ORANGE); button_5.setFont(new Font("楷体", Font.BOLD, 26));
        button_6 = new JButton("6"); button_6.setBackground(Color.ORANGE); button_6.setFont(new Font("楷体", Font.BOLD, 26));
        button_7 = new JButton("7"); button_7.setBackground(Color.ORANGE); button_7.setFont(new Font("楷体", Font.BOLD, 26));
        button_8 = new JButton("8"); button_8.setBackground(Color.ORANGE); button_8.setFont(new Font("楷体", Font.BOLD, 26));
        button_9 = new JButton("9"); button_9.setBackground(Color.ORANGE); button_9.setFont(new Font("楷体", Font.BOLD, 26));
        button_0 = new JButton("0"); button_0.setBackground(Color.ORANGE); button_0.setFont(new Font("楷体", Font.BOLD, 26));
        
        button_C  = new JButton("C");   button_C.setBackground(Color.WHITE); button_C.setFont(new Font("楷体", Font.BOLD, 26));  
        button_BS = new JButton("BS"); button_BS.setBackground(Color.WHITE); button_BS.setFont(new Font("楷体", Font.BOLD, 26));
        button_CE = new JButton("CE");  button_CE.setBackground(Color.WHITE); button_CE.setFont(new Font("楷体", Font.BOLD,  26));  
        
        button_pm = new JButton("+/-"); button_pm.setBackground(Color.PINK);  button_pm.setFont(new Font("楷体", Font.BOLD, 26));
        button_div = new JButton("/");  button_div.setBackground(Color.PINK); button_div.setFont(new Font("楷体", Font.BOLD, 26));
        button_mul = new JButton("*");  button_mul.setBackground(Color.PINK); button_mul.setFont(new Font("楷体", Font.BOLD, 26));
        button_sub = new JButton("-");  button_sub.setBackground(Color.PINK); button_sub.setFont(new Font("楷体", Font.BOLD, 26));
        button_pot = new JButton(".");  button_pot.setBackground(Color.PINK); button_pot.setFont(new Font("楷体", Font.BOLD, 26));
        button_eql = new JButton("=");  button_eql.setBackground(Color.PINK); button_eql.setFont(new Font("楷体", Font.BOLD, 26));
        button_pls = new JButton("+");  button_pls.setBackground(Color.PINK); button_pls.setFont(new Font("楷体", Font.BOLD, 26));
        
        button_lft = new JButton("(");    button_lft.setBackground(Color.lightGray); button_lft.setFont(new Font("楷体", Font.BOLD, 26));
        button_rgt = new JButton(")");    button_rgt.setBackground(Color.lightGray); button_rgt.setFont(new Font("楷体", Font.BOLD, 26));
        button_log = new JButton("ln");   button_log.setBackground(Color.lightGray); button_log.setFont(new Font("楷体", Font.BOLD, 26));
        button_sqt = new JButton("sqrt"); button_sqt.setBackground(Color.lightGray); button_sqt.setFont(new Font("楷体", Font.BOLD, 26));
        button_exp = new JButton("exp");  button_exp.setBackground(Color.lightGray); button_exp.setFont(new Font("楷体", Font.BOLD, 26));
        button_tan = new JButton("tan");  button_tan.setBackground(Color.lightGray); button_tan.setFont(new Font("楷体", Font.BOLD, 26));
        button_cos = new JButton("cos");  button_cos.setBackground(Color.lightGray); button_cos.setFont(new Font("楷体", Font.BOLD, 26));
        button_sin = new JButton("sin");  button_sin.setBackground(Color.lightGray); button_sin.setFont(new Font("楷体", Font.BOLD, 26));
        button_abs = new JButton("abs");  button_abs.setBackground(Color.lightGray); button_abs.setFont(new Font("楷体", Font.BOLD, 26));
        button_int = new JButton("int");  button_int.setBackground(Color.lightGray); button_int.setFont(new Font("楷体", Font.BOLD, 26));
        
        
        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayout(1, 6, 4, 4));
        this.add(panel1);
        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(1, 6, 4, 4));
        this.add(panel2);
        JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayout(1, 6, 4, 4));
        this.add(panel3);
        JPanel panel4 = new JPanel();
        panel4.setLayout(new GridLayout(1, 6, 4, 4));
        this.add(panel4);
        JPanel panel5 = new JPanel();
        panel5.setLayout(new GridLayout(1, 6, 4, 4));
        this.add(panel5); 

        panel1.add(button_log); panel1.add(button_exp); panel1.add(button_CE); 
        panel1.add(button_C); panel1.add(button_BS); panel1.add(button_div);
        panel2.add(button_sin); panel2.add(button_cos); panel2.add(button_7); 
        panel2.add(button_8); panel2.add(button_9); panel2.add(button_mul);
        panel3.add(button_tan); panel3.add(button_sqt); panel3.add(button_4); 
        panel3.add(button_5); panel3.add(button_6); panel3.add(button_sub);
        panel4.add(button_abs); panel4.add(button_int); panel4.add(button_1); 
        panel4.add(button_2); panel4.add(button_3); panel4.add(button_pls);
        panel5.add(button_lft); panel5.add(button_rgt); panel5.add(button_pm); 
        panel5.add(button_0); panel5.add(button_pot); panel5.add(button_eql);

        button_1.addActionListener(this); button_2.addActionListener(this); 
        button_3.addActionListener(this); button_4.addActionListener(this); 
        button_5.addActionListener(this); button_6.addActionListener(this); 
        button_7.addActionListener(this); button_8.addActionListener(this); 
        button_9.addActionListener(this); button_0.addActionListener(this);
        button_pm.addActionListener(this); button_pot.addActionListener(this); 
        button_BS.addActionListener(this); button_C.addActionListener(this);
        button_CE.addActionListener(this); button_eql.addActionListener(this); 
        button_pls.addActionListener(this); button_sub.addActionListener(this); 
        button_mul.addActionListener(this); button_div.addActionListener(this); 
        button_log.addActionListener(this); button_exp.addActionListener(this);
        button_tan.addActionListener(this); button_sqt.addActionListener(this);  
        button_cos.addActionListener(this); button_sin.addActionListener(this);
        button_lft.addActionListener(this); button_rgt.addActionListener(this); 
        button_abs.addActionListener(this); button_int.addActionListener(this); 
        
        init();

        this.addWindowListener(new WinClose());		//注册窗口监听器
        this.setVisible(true);    
    }

    public void actionPerformed(ActionEvent e) {       //按钮的单击事件处理方法
    	
    	String input = e.getActionCommand();
    	
        if( e.getSource().equals(button_1) || e.getSource().equals(button_2) || 
            e.getSource().equals(button_3) || e.getSource().equals(button_4) ||
            e.getSource().equals(button_5) || e.getSource().equals(button_6) ||
            e.getSource().equals(button_7) || e.getSource().equals(button_8) ||
            e.getSource().equals(button_9) || e.getSource().equals(button_0) ||
            e.getSource().equals(button_pot) || e.getSource().equals(button_pm) ||
            e.getSource().equals(button_lft) || e.getSource().equals(button_rgt)) {      //非运算符的处理方法          
        	
            if(start) {
            	
            	if(lastCommand.equals("=")) init();
            	
                displayField.setText("");
                start = false;
                flg = false;
                if(input.equals("+/-")) {
                	displayField.setText("-");
                	flg = true;
                }
            }
            String str = displayField.getText();
            if(input.equals("+/-")) {
            	if(flg) displayField.setText(str.substring(1));
            	else displayField.setText("-" + str);
            	flg = !flg;
            }
            else {
            	if(input.equals("(")) cnt++;
            	else if(input.equals(")")) cnt--;
                displayField.setText(str + input);
            }
        }
        else if(e.getSource().equals(button_C) || e.getSource().equals(button_BS) ||
                e.getSource().equals(button_CE) ) {
        	String str = displayField.getText();
        	if(input.equals("BS")) {        // BackSpace
            	if(str.length() > 0)
                    displayField.setText(str.substring(0, str.length() - 1));
            }
            else if(input.equals("CE")) {   // CE
            	displayField.setText("0");
                start = true;
            }
            else if(input.equals("C")) {	// C
                init();
            }
        }
        else if(e.getSource().equals(button_log) || e.getSource().equals(button_exp) ||
        		e.getSource().equals(button_sin) || e.getSource().equals(button_cos) ||
        		e.getSource().equals(button_tan) || e.getSource().equals(button_sqt) ||
        		e.getSource().equals(button_abs) || e.getSource().equals(button_int)) {
        	
        	String command = e.getActionCommand();        
        	String tmp = displayField.getText();

//        	System.out.println("question_2:  " + tmp);
        	double now = Lib.calc(tmp);
        	
        	if (command.equals("sqrt")) now = Math.sqrt(now);
            else if (command.equals("exp")) now = Math.exp(now); 
            else if (command.equals("ln"))  now = Math.log(now);
            else if (command.equals("tan")) now = Math.tan(now);
            else if (command.equals("cos")) now = Math.cos(now);
            else if (command.equals("sin")) now = Math.sin(now);
            else if (command.equals("abs")) now = Math.abs(now);
            else if (command.equals("int")) now = Math.floor(now);
        	
        	if(tmp.startsWith("(")) questionField.setText(questionField.getText() + lastCommand + command + tmp);
        	else questionField.setText(questionField.getText() + lastCommand + command + "(" + tmp + ")");
        	
        	question = question + lastCommand + now;
        	
        	ans = Lib.calc(question);
//        	System.out.println("question_1:  " + question);
        	displayField.setText("" + ans);
        	
        	start = true;
        }
        else {
            String command = e.getActionCommand();
  
            if(start) lastCommand = command;
            else if(cnt != 0) {
            	displayField.setText(displayField.getText() + input);
            }
            else {
            	questionField.setText(questionField.getText() + lastCommand + displayField.getText());
                question = question + lastCommand + displayField.getText();

//            	System.out.println("question_3:  " + question);
                ans = Lib.calc(question);
//                System.out.println(ans);
                displayField.setText("" + ans);
                lastCommand = command;
                start = true;
            }
         }
    }
    
    public void init() {
    	start = true;
        ans = 0;
        cnt = 0;
        lastCommand = "";
        question = "";
        displayField.setText("0");
    	questionField.setText("");
    }

    public void windowClosing(WindowEvent e){
        if(e.getSource() == dialog)
            dialog.setVisible(false);           //隐藏对话框
        else
            System.exit(0); 
    }

    public void windowOpened(WindowEvent e)         {  }
    public void windowActivated(WindowEvent e)      {  }
    public void windowDeactivated(WindowEvent e)    {  }
    public void windowClosed(WindowEvent e)         {  }
    public void windowIconified(WindowEvent e)      {  }
    public void windowDeiconified(WindowEvent e)    {  }

    public static void main(String args[]) {
        @SuppressWarnings("unused")
		Main calculator = new Main();
    }
}

class WinClose implements WindowListener
{
    public void windowClosing(WindowEvent e) {    //单击窗口关闭按钮时触发并执行实现窗口监听器接口中的方法
        System.exit(0);          //结束程序运行
    }
    public void windowOpened(WindowEvent e){ }
    public void windowActivated(WindowEvent e){}
    public void windowDeactivated(WindowEvent e){ }
    public void windowClosed(WindowEvent e){ }
    public void windowIconified(WindowEvent e){ }
    public void windowDeiconified(WindowEvent e){ }
}