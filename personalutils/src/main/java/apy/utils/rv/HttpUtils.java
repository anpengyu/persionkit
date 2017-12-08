package apy.utils.rv;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 获取http请求的工具类
 * */
public class HttpUtils {
	/**
	 * 通过HttpURLConnection获得字符串数据
	 * @param ：params--->网络请求地址
	 * 
	 * @return  返回请求获得字符串数据
	 * */
	public static String getJsonContent(String params){
		String result = "";
		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		try {
			URL url = new URL(params);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			is = connection.getInputStream();
			isr = new InputStreamReader(is,"utf-8");
			br = new BufferedReader(isr);
			String line = "";
			while ((line=br.readLine())!=null) {
				result+=line;
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if (is!=null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (isr!=null) {
				try {
					isr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (br!=null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

}
