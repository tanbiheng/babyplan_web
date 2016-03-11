package com.gem.babyplan.utils;

import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * @author 炳华儿 E-mail: 574583006@qq.com
 * @date 创建时间：2016年1月26日 下午4:20:15
 * @parameter
 * @return
 */
// 这是一个封装类
public class HttpMessyCode extends HttpServletRequestWrapper {

	// 通过下面的方法，就解决了所有的请求过来的编码问题，额，以后和过滤器一起直接丢到某个地方，在配置一下就可以了

	/**
	 * @param request
	 */
	// 创建一个类成员，用来接收请求
	HttpServletRequest request = null;

	/**
	 * @param request
	 */
	public HttpMessyCode(HttpServletRequest request) {
		super(request);
		// TODO Auto-generated constructor stub
		this.request = request;
	}

	// 在这里把原来的东西进行处理

	@Override
	public Map<String, String[]> getParameterMap() {
		// 最难处理，解决即可解决其他。
		// 先判断是否为post提交.post提交就简单，还有get提交，另外其他就不管了
		// 而由于tomcat服务器里面第一次解析map的时候，第二次就不解析了，所以需要设置一个全局变量标识符
		boolean flag = true;
		if (request.getMethod().equalsIgnoreCase("post")) {
			try {
				request.setCharacterEncoding("utf-8");
				// 编码之后，返回这个map
				return request.getParameterMap();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RuntimeException("不支持编码");
			}
		} else if (request.getMethod().equalsIgnoreCase("get")) {
			Map<String, String[]> map = request.getParameterMap();
			// get方式最麻烦！！
			if (flag) {

				// 对map进行遍历出来，把每一个字符串全部成相应的编码嘛事
				for (Map.Entry<String, String[]> entry : map.entrySet()) {
					// 得到值,将这个数组的每一个元素进行解码
					String strings[] = entry.getValue();
					for (String string : strings) {

						try {
							// 将每一个字符串进行重新编码
							string = new String(string.getBytes("ISO8859-1"), "utf-8");
						} catch (UnsupportedEncodingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							throw new RuntimeException("不支持编码");
						}
					}

				}
				flag = false;// 设置为false,第二次就不会再进这个代码块了
			}
			return map;

		}
		// 其他方式不管了
		else {

			return super.getParameterMap();
		}
	}

	@Override
	public Enumeration<String> getParameterNames() {
		// 和前面就大同小异了
		try {
			if (request.getMethod().equalsIgnoreCase("post")) {
				request.setCharacterEncoding("utf-8");
				return request.getParameterNames();
			} else if (request.getMethod().equalsIgnoreCase("get")) {
				// 取出来每一个，重新编码
				Enumeration<String> en = request.getParameterNames();
				while (en.hasMoreElements()) {
					String name = (String) en.nextElement();
					// 得到值
					String value = request.getParameter(name);
					// 重新编译
					value = new String(value.getBytes("ISO8859-1"), "utf-8");

				}
				return en;
			} else {

				return super.getParameterNames();
			}
			// TODO Auto-generated method stub
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("编码错误");
		}
	}

	@Override
	public String[] getParameterValues(String name) {
		// 这个简单,相当于map里面的一项
		return (String[]) getParameterMap().get(name);
	}

	@Override
	public String getParameter(String name) {
		// 很简单了.
		return getParameterValues(name) == null ? null : getParameterValues(name)[0];
	}

}
