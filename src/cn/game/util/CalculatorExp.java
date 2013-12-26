package cn.game.util;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.*;

public class CalculatorExp {
	protected static StringBuffer sb = new StringBuffer(); // 储存计算过程的StringBuffer

	public CalculatorExp() {

	}

	@SuppressWarnings("unchecked")
	public double CalcExp(String expression) {
		StackTraceElement[] items = Thread.currentThread().getStackTrace();
		System.out.println(items[2].getClassName());
		System.out.println(items[2].getMethodName());
		
		
		
		try {
			sb.setLength(0);
			sb.append("0.原等式为：" + expression + "\n");
			sb.append("1.检查算式 " + "\n");
			boolean okay = Check(expression); // 检查"("和")"的数量是否相等                                                                                       

			if (okay == true) {
				sb.append("    (1).算式正确.\n");

				sb.append("2.负号前加0\n");
				Pattern pt = Pattern.compile("\\(-");
				Matcher mt = pt.matcher(expression);
				String s = new String();
				s = mt.replaceAll("(0-");
				sb.append("    (1).等式标准格式化为：" + s + "\n");

				sb.append("3.开始计算.\n");
				Pattern ptn = Pattern.compile("\\d+\\.\\d+|\\d+|\\(|\\)|\\+|\\-|\\*|\\/");
				Deque stack = new LinkedList(); // 运算过程中要使用的栈
				LinkedList list = new LinkedList(); // 运算最后的列表
				Matcher mtr = ptn.matcher(s);
				while (mtr.find()) {
					String nodeString = s.substring(mtr.start(), mtr.end());
					if (nodeString.matches("\\d+\\.\\d+|\\d+")) {
						sb.append("    (1).数字 \"" + nodeString + "\" 入栈.\n");
						list.push(Double.valueOf(nodeString));
					} else {
						OPNode opn = new OPNode(nodeString);
						int peekLevel = (stack.peek() == null) ? 0
								: ((new OPNode(stack.peek().toString())).level);
						if (opn.level == -1) {
							sb.append("    (1).出现 \"" + nodeString
									+ "\" 时,stack出栈,直到出现\")\"\n");
							Deque tempDe = new LinkedList(); // 临时Deque,储存没有括号的算式
							String temp = new String();
							if ((new OPNode(temp = stack.peek().toString())).level == -3) {
								Deque dd = new LinkedList();
								dd.push(stack.poll().toString());
								// tempDe.push(list.poll());
							} else {
								while ((new OPNode(temp = stack.poll()
										.toString())).level != -3) {
									tempDe.push(list.poll());
									tempDe.push(temp);
								}
							}
							if (list.peek() != null) {
								tempDe.push(list.poll());
							}
							list.push(CalcSimExp(tempDe));
						} else if (opn.level > peekLevel) {
							sb.append("    (1)." + opn.level + " >= "
									+ peekLevel + ", \"" + nodeString
									+ "\" 入栈.\n");
							stack.push(nodeString);
						} else {
							if (opn.level == -3) {
								sb.append("    (1).出现 \"" + nodeString
										+ "\" 时,一直入stack栈.\n");
								stack.push(nodeString);
							} else {
								sb.append("    (1).其它运算符情况: \"" + nodeString
										+ "\" 入栈\n");
								stack.push(nodeString);
							}
						}
					}
				}
				Deque tempDeque = new LinkedList();
				while (stack.peek() != null) {
					tempDeque.push(list.poll());
					tempDeque.push(stack.poll());
				}
				tempDeque.push(list.poll());
				double result = 1;
				result = CalcSimExp(tempDeque);
				sb.append("    (1).结果为：" + result + "\n");
				sb.append("4.输出计算结果.\n");
				// Calculator.resultScreen.setText(""+result);
				// System.out.print(sb.toString()+"\n");
				sb.append("    (1).结果为：" + result + "\n");
				// 如果存在"="，不添加"="
				
				//System.out.print(sb);
				return result;
			} else {

			}

		} catch (Exception es) {
			// Calculator.resultScreen.setText("错误.\n");
			// System.out.println(es.getMessage());
			// System.out.println("错误.");
			es.printStackTrace();
			sb.append("计算错误.\n");
		}
		return 0.0;
	}

	public boolean Check(String expression) {
		boolean boo = false;

		boolean boo1 = false; // 判断(和)是否相等
		boolean boo2 = false; // 判断数位长度是否大于7
		boolean boo3 = false; // 判断expression是否为空
		boolean boo4 = false; // 判断等号后边是否有数字

		Pattern pt1 = Pattern.compile("\\(");
		Pattern pt2 = Pattern.compile("\\)");
		Matcher mt1 = pt1.matcher(expression);
		Matcher mt2 = pt2.matcher(expression);
		int lparnum = 0;
		int rparnum = 0;
		while (mt1.find()) {
			lparnum++;
		}
		while (mt2.find()) {
			rparnum++;
		}
		if (lparnum == rparnum) {
			boo1 = true;
		}

		Pattern pt3 = Pattern.compile("\\d{8,}");
		Matcher mt3 = pt3.matcher(expression);
		if (!mt3.find()) {
			boo2 = true;
		}

		if (expression.length() != 0) {
			boo3 = true;
		}

		Pattern pt4 = Pattern.compile("=\\d+");
		Matcher mt4 = pt4.matcher(expression);
		if (!mt4.find()) {
			boo4 = true;
		}
		if ((boo1 == true) && (boo2 == true) && boo3 == true && boo4 == true) {
			boo = true;
		}

		if (boo1 == false) {
			sb.append("左右括号数目不一至,请重新输入.\n");
		}
		if (boo2 == false) {
			sb.append("数字位数大于7,请重新输入.\n");
		}
		if (boo3 == false) {
			sb.append("算式为空,请输入算式.\n");
		}
		return boo;
	}

	@SuppressWarnings("unchecked")
	public double CalcSimExp(Deque tempDe) {
		LinkedList number = new LinkedList();
		Deque operation = new LinkedList();
		String num = "\\d+\\.\\d+|\\d+|\\-\\d+|\\-\\d+\\.\\d+";
		String oper = "\\+|\\-|\\*|\\/";
		Pattern pt1 = Pattern.compile(num);
		Pattern pt2 = Pattern.compile(oper);
		if (number.size() == 1) {
			return Double.parseDouble(number.poll().toString());
		} else {
			while (tempDe.peek() != null) {
				String s1 = new String();
				s1 = tempDe.poll().toString();
				Matcher mt1 = pt1.matcher(s1);
				Matcher mt2 = pt2.matcher(s1);
				if (mt1.matches()) {
					sb.append("        (2).数字入栈：" + s1 + "\n");
					number.push(Double.parseDouble(s1));

				} else if (mt2.matches()) {
					OPNode op = new OPNode(s1);
					if (op.level == 1) {
						sb.append("        (2).一级符号" + s1 + "入栈\n");
						operation.push(s1);
					} else if (op.level == 2) {
						sb.append("        (2).三级符号" + s1
								+ "出现,取出number的两个数字进行计算.\n");
						number.push(tempDe.pop());
						double double1 = Double.parseDouble(number.poll()
								.toString());
						double double2 = Double.parseDouble(number.poll()
								.toString());
						number.push(SimpleCalc(double2, s1, double1));
					}
				}
			}
			sb.append("        (2).运算符只剩下\"+\"和\"-\"了，处理:\n");
			LinkedList link = new LinkedList();

			if (operation.peek() != null) {
				while (operation.peek() != null) {
					link.push(Double.parseDouble(number.poll().toString()));
					link.push(operation.poll().toString());
				}
				link.push(Double.parseDouble(number.poll().toString()));
				while (link.size() != 1) {
					double double1 = Double.parseDouble(link.poll().toString());
					String st = link.poll().toString();
					double double2 = Double.parseDouble(link.poll().toString());
					link.push(SimpleCalc(double1, st, double2));
				}
				return Double.parseDouble(link.poll().toString());
			} else {
				return Double.parseDouble(number.poll().toString());
			}
		}
	}

	public double SimpleCalc(double d1, String s, double d2) {
		int i = 0;
		sb.append("            (3).第一个数字：" + d1 + "\n");
		sb.append("            (3).运算符：" + s + "\n");
		sb.append("            (3).第二个数字：" + d2 + "\n");
		Pattern pt1 = Pattern.compile("\\+");
		Pattern pt2 = Pattern.compile("\\-");
		Pattern pt3 = Pattern.compile("\\*");
		Pattern pt4 = Pattern.compile("\\/");
		Matcher mt1 = pt1.matcher(s);
		Matcher mt2 = pt2.matcher(s);
		Matcher mt3 = pt3.matcher(s);
		Matcher mt4 = pt4.matcher(s);

		if (mt1.matches()) {
			i = 1;
		} else if (mt2.matches()) {
			i = 2;
		} else if (mt3.matches()) {
			i = 3;
		} else if (mt4.matches()) {
			i = 4;
		} else {
			sb.append("出错了：");
		}
		double d = 1;
		switch (i) {
		case 1:
			d = d1 + d2;
			break;
		case 2:
			d = d1 - d2;
			break;
		case 3:
			d = d1 * d2;
			break;
		case 4:
			d = d1 / d2;
			break;
		}
		sb.append("            (3).计算结果：" + d + "\n");
		return d;
	}

	public static void main(String[] args)
	{
		CalculatorExp calce = new CalculatorExp();
		double d = calce.CalcExp("0\n(1000/10+23)"); // 左此修改算式
		System.out.println(sb.toString());
		System.out.println("计算结果为：" + d);
	}
}

class OPNode {
	char op; // 运算符符号
	int level; // 运算符优先级

	public OPNode(String op) {
		this.op = op.charAt(0);
		if (op.equals("+") || op.equals("-")) {
			this.level = 1; // 加减运算符为1
		} else if (op.equals("*") || op.equals("/")) {
			this.level = 2; // 乘除运算符为2
		} else if (op.equals("(")) {
			this.level = -3; // "("优先级为-3
		} else if (op.equals(")")) {
			this.level = -1; // ")"优先级为-1
		}
	}

}
