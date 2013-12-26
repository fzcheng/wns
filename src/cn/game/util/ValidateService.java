package cn.game.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
 

/**
 * @author hhj 验证用户的输入，返回验证失败的字符串，返回为空时表示验证成功
 */
public class ValidateService
{

	/**
	 * 验证角色名的合法性
	 * 
	 * @param role_name
	 * @return
	 */
	public String validateRoleName(String uPk, String role_name)
	{
		String hint = null;

		// 用户名匹配为字符型
		Pattern p = Pattern.compile(Expression.chinese_regexp);
		Matcher m = p.matcher(role_name);
		boolean b = m.matches();
		if (b == false)
		{
			return "数字,英文或中文字符组合";
		}

		if (role_name.indexOf(" ") != -1)
		{
			return hint = "不能有空格";
		}

		if (role_name == null || role_name.equals(""))
		{
			return hint = "不能为空";
		}

		if (role_name.length() < 2)
		{
			return hint = "角色名长度不够";
		}
		if (role_name.length() > 5)
		{
			return hint = "角色名长度超过限制";
		}
		
		if (Expression.hasWeiFaChar(role_name))
		{
			return "名字中请不要有gm、客服等字样!";
		}
		
		return hint;
	}

	/**
	 * 验证用户名的合法性
	 * 
	 * @param user_name
	 * @return
	 */
	public String validateUserName(String user_name)
	{
		String hint = null;
		// 用户名匹配为字符型
		Pattern p = Pattern.compile(Expression.chinese_regexp);
		Matcher m = p.matcher(user_name);
		boolean b = m.matches();
		if (b == false)
		{
			return "请输入大小写英文字符和数字汉字组成的名称";
		}

		if (user_name.indexOf(" ") != -1)
		{
			return "名称不能有空格出现";
		}
		if (user_name == null || user_name.equals(""))
		{
			return "名称不能为空";
		}
		if (user_name.length() < 6)
		{
			return "名称位数不能小于6位";
		}
		else
			if (user_name.length() > 12)
			{
				return "名称位数不能大于12位";
			}

		if (Expression.hasWeiFaChar(user_name))
		{
			return "名字中请不要有gm、客服等字样!";
		}
		return hint;
	}

	/**
	 * 验证昵称的合法性
	 * 
	 * @param user_name
	 * @return
	 */
	public String validateNickName(String user_name)
	{
		String hint = null;
		// 用户名匹配为字符型
		Pattern p = Pattern.compile(Expression.chinese_regexp);
		Matcher m = p.matcher(user_name);
		boolean b = m.matches();
		if (b == false)
		{
			return "请输入大小写英文字符和数字汉字组成的昵称";
		}

		if (user_name.indexOf(" ") != -1)
		{
			return "昵称不能有空格出现";
		}
		if (user_name == null || user_name.equals(""))
		{
			return "昵称不能为空";
		}
		if (user_name.length() < 2)
		{
			return "昵称位数不能小于2位";
		}
		else
			if (user_name.length() > 12)
			{
				return "昵称位数不能大于12位";
			}

		if (Expression.hasWeiFaChar(user_name))
		{
			return "昵称中请不要有gm、客服等字样!";
		}
		return hint;
	}
	
	/**
	 * 验证密码的合法性
	 * 
	 * @param user_name
	 * @return
	 */
	public String validatePwd(String pwd)
	{
		String hint = null;

		Pattern p = Pattern.compile(Expression.letter_number_regexp);
		Matcher pw = p.matcher(pwd);
		boolean pp = pw.matches();

		if (pp == false)
		{
			return "密码请输入大小写英文字符和数字字符";
		}

		if (pwd.length() < 8)
		{
			return "密码位数不能小于8位";
		}
		else
			if (pwd.length() > 16)
			{
				return "密码位数不能大于16位";
			}

		if (pwd.indexOf(" ") != -1)
		{
			return "密码不能有空格出现";
		}
		if (pwd == null || pwd.equals(""))
		{
			return "密码不能为空";
		}

		return hint;
	}

	/**
	 * 验证注册账号的用户名和密码的合法性
	 * 
	 * @return
	 */
	public String validateRegisterUsernameAndPwd(String user_name, String pwd)
	{
		String hint = null;
		Pattern p = Pattern.compile(Expression.letter_number_regexp);
		if (user_name.length() > 11)
		{
			return "对不起，游戏帐号为6-11位数字和大小写英文字符组合；密码为6位数字和大小写英文字符组合。重新注册！";
		}

		if (pwd.length() != 6)
		{
			return "对不起，游戏帐号为6-11位数字和大小写英文字符组合；密码为6位数字和大小写英文字符组合。重新注册！";
		}

		// 验证用户名格式的合法性
		if (user_name == null || user_name.equals(""))
		{
			return "请输入正确的用户名!";
		}

		Matcher m1 = p.matcher(user_name);
		boolean b1 = m1.matches();
		if (b1 == false)
		{
			return "请输入正确的用户名!";
		}
		// 验证密码格式的合法性
		if (pwd == null || pwd.equals(""))
		{
			return "请输入正确的密码!";
		}

		Matcher m2 = p.matcher(pwd);
		boolean b2 = m2.matches();
		if (b2 == false)
		{
			return "请输入正确的密码!";
		}
		 
		hint = validateUserName(user_name);

		if (hint != null)
		{
			return "对不起，此帐号已经注册。重新注册！";
		}

		return hint;
	}
	
	/**
	 * 验证是否是非0正整数
	 */
	public String validateNonZeroNegativeIntegers(String num)
	{
		String hint = null;
		
		if( num==null )
		{
			return hint = "非法输入";
		}
		
		// 用户名匹配为字符型
		Pattern p = Pattern.compile(Expression.positive_integer_contain0_regexp);
		Matcher m = p.matcher(num);
		if(!m.matches())
		{
			hint = "非法输入";
		}
		
		return hint;
	}

	
	/**
	 * 验证帮派名称的合法性
	 * 
	 * @param role_name
	 * @return
	 */
	public String validateTongName(String tong_name)
	{
		String hint = null;

		// 用户名匹配为字符型
		Pattern p = Pattern.compile(Expression.chinese_regexp);
		Matcher m = p.matcher(tong_name);
		boolean b = m.matches();
		if (b == false)
		{
			return "帮派名称为数字,英文或中文字符组合";
		}

		if (tong_name.indexOf(" ") != -1)
		{
			return hint = "帮派名称不能有空格";
		}

		if (tong_name == null || tong_name.equals(""))
		{
			return hint = "帮派名称不能为空";
		}

		if (Expression.hasWeiFaChar(tong_name))
		{
			return "帮派名称中请不要有gm、客服等字样!";
		}
		 
		if (tong_name.length() > 5)
		{
			return "帮派名称不能长于5位";
		}

		return hint;
	}
	
	/**
	 * 验证绑定手机的合法性
	 * 
	 * @param user_name
	 * @return
	 */
	public String validatePhone(String Phone)
	{
		String hint = null;

		Pattern p = Pattern.compile(Expression.integer_regexp);
		Matcher pw = p.matcher(Phone);
		boolean pp = pw.matches();
		if (pp == false)
		{
			return "手机号请输入11位的数字字符";
		}		
		if (Phone == null || Phone.equals("") || Phone.length()!=11) {
			return "绑定的手机号填写错误";
		}
		String validateStr=Phone.substring(0,3);
		int validate = Integer.parseInt(validateStr);
		//国际分配给中国的频段130 - 139 ,150 -159 ,182-189
		if (validate <130 || validate >189 || validate == 157 || validate == 167 || (validate >139 && validate <150) || (validate >159 && validate <180 ) ) {
			return "请输入正确的手机号码";
		}
		
		return hint;
	}

}
