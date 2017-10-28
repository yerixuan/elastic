//package com.elastic.elastic;
//
//import com.feidee.money.common.EnvContext;
//import com.feidee.money.common.cryptograph.Base64;
//import okhttp3.Call;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.Response;
//import org.apache.http.*;
//import org.apache.http.client.ClientProtocolException;
//import org.apache.http.client.ResponseHandler;
//import org.apache.http.client.config.RequestConfig;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.entity.ContentType;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.message.BasicNameValuePair;
//import org.apache.http.util.EntityUtils;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.PrintWriter;
//import java.net.URL;
//import java.net.URLConnection;
//import java.nio.charset.Charset;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import java.util.Map.Entry;
//
//public class HttpUtils {
//	private static final RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(30000).setConnectTimeout(30000)
//			.setSocketTimeout(30000).build();
//
//	private static final CloseableHttpClient httpclient = HttpClients.custom().setMaxConnTotal(100).setMaxConnPerRoute(100)
//			.setDefaultRequestConfig(requestConfig).build();
//
//	private static final ResponseHandler<StringResponse> responseHandler = new ResponseHandler<StringResponse>() {
//		@Override
//		public StringResponse handleResponse(final HttpResponse response) throws ClientProtocolException, IOException {
//			int status = response.getStatusLine().getStatusCode();
//			HttpEntity entity = response.getEntity();
//			StringResponse stringResponse = new StringResponse();
//			stringResponse.setStatusCode(status);
//			ContentType ct = ContentType.get(entity);
//			Charset charset = ct != null ? ct.getCharset() : null;
//			if (charset == null) {
//				charset = Charset.defaultCharset();
//			}
//			stringResponse.setCharset(charset);
//
//			stringResponse.setResponseBody(entity != null ? EntityUtils.toString(entity) : null);
//			return stringResponse;
//
//		}
//
//	};
//
//	/**
//	 * 处理resepone返回字节类型的结果
//	 */
////	private static final ResponseHandler<BinaryResponse> binaryResponseHandler = new ResponseHandler<BinaryResponse>() {
////		@Override
////		public BinaryResponse handleResponse(final HttpResponse response) throws ClientProtocolException, IOException {
////			int status = response.getStatusLine().getStatusCode();
////			HttpEntity entity = response.getEntity();
////			BinaryResponse myResponse = new BinaryResponse();
////			myResponse.setFileName(getFileName(response));
////			myResponse.setStatusCode(status);
////			ContentType contentType = ContentType.get(entity);
////			String mimeType = null;
////			if (contentType != null) {
////				mimeType = ContentType.get(entity).getMimeType();
////			}
////
////			myResponse.setMimeType(mimeType);
////			byte[] bytes = entity != null ? EntityUtils.toByteArray(entity) : null;
////			myResponse.setResponseBody(bytes);
////
////			return myResponse;
////
////		}
////
////	};
//	public static String getFileName(HttpResponse response) {
//		Header contentHeader = response.getFirstHeader("Content-Disposition");
//		String filename = null;
//		if (contentHeader != null) {
//			HeaderElement[] values = contentHeader.getElements();
//			if (values.length == 1) {
//				NameValuePair param = values[0].getParameterByName("filename");
//				if (param != null) {
//					try {
//						filename = param.getValue();
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//				}
//			}
//		}
//		return filename;
//	}
//
//	public static StringResponse get(String url) throws Exception {
//		try {
//			HttpGet httpget = new HttpGet(url);
//			return httpclient.execute(httpget, responseHandler);
//		} catch (ClientProtocolException e) {
//			throw e;
//		} catch (IOException e) {
//			throw e;
//		}
//	}
//
//	public static StringResponse post(String url) throws Exception {
//		try {
//			HttpPost httppost = new HttpPost(url);
//			return httpclient.execute(httppost, responseHandler);
//		} catch (ClientProtocolException e) {
//			throw e;
//		} catch (IOException e) {
//			throw e;
//		}
//	}
//
//	public static StringResponse post(String url, Map<String, String> paramMap) throws Exception {
//		try {
//			HttpPost httppost = new HttpPost(url);
//
//			List<NameValuePair> nps = map2NameValuePairs(paramMap);
//			UrlEncodedFormEntity en = new UrlEncodedFormEntity(nps, Consts.UTF_8);
//			en.setContentEncoding("UTF-8");
//			httppost.setEntity(en);
//
//			return httpclient.execute(httppost, responseHandler);
//		} catch (ClientProtocolException e) {
//			throw e;
//		} catch (IOException e) {
//			throw e;
//		}
//	}
//
//	public static String sendPost(String url, String param) {
//		PrintWriter out = null;
//		BufferedReader in = null;
//		String result = "";
//		try {
//
//
//
//			String auth = new String(Base64.encode((EnvContext.getConfig().get("es.userName") + ":" + EnvContext.getConfig().get("es.passWord")).getBytes())).replace("\r\n", "");
//			URL realUrl = new URL(url);
//			URLConnection conn = realUrl.openConnection();
//			conn.setRequestProperty("accept", "*/*");
//			conn.setRequestProperty("Authorization", "Basic " + auth);
//			conn.setRequestProperty("connection", "Keep-Alive");
//			conn.setRequestProperty("user-agent",
//					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
//			conn.setDoOutput(true);
//			conn.setDoInput(true);
//			out = new PrintWriter(conn.getOutputStream());
//			out.print(param);
//			out.flush();
//			in = new BufferedReader(
//					new InputStreamReader(conn.getInputStream(), "utf-8"));
//			String line;
//			while ((line = in.readLine()) != null) {
//				result += line;
//			}
//		} catch (Exception e) {
//			System.out.println("发送 POST 请求出现异常！" + e);
//			e.printStackTrace();
//		} finally {
//			try {
//				if (out != null) {
//					out.close();
//				}
//				if (in != null) {
//					in.close();
//				}
//			} catch (IOException ex) {
//				ex.printStackTrace();
//			}
//		}
//		return result;
//	}
//
//	public static StringResponse json(String url, String body) throws Exception {
//		try {
//			HttpPost method = new HttpPost(url);
//			StringEntity stringEntity = new StringEntity(body, Consts.UTF_8);
//			stringEntity.setContentEncoding("UTF-8");
//			stringEntity.setContentType("application/json");
//			method.setEntity(stringEntity);
//			return httpclient.execute(method, responseHandler);
//		} catch (Exception e) {
//			throw e;
//		}
//	}
//
//	public static List<NameValuePair> map2NameValuePairs(final Map<String, String> params) {
//		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
//		if (params == null) {
//			return nameValuePairs;
//		}
//		for (Entry<String, String> entry : params.entrySet()) {
//			nameValuePairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
//		}
//		return nameValuePairs;
//	}
//
//	public static BinaryResponse getBinary(String url) throws ClientProtocolException, IOException {
//		HttpGet get = new HttpGet(url);
//		BinaryResponse myResponse = httpclient.execute(get, binaryResponseHandler);
//		if (myResponse.getFileName() == null)
//			myResponse.setFileName(url.substring(url.lastIndexOf("/") + 1));
//		return myResponse;
//	}
//
//	public static void main(String[] args) {
//		try {
//			post("http://127.0.0.1:8080/cardniu-monitor/login/doLogin?userName=fujinlong");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//}