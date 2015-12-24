package com.taojia.app.Utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;



import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

/**
 * HTTP宸ュ叿绫�
 * 
 * @author lixiangyang
 * 
 */
public class HttpUtils {

    private static final Logger logger = LoggerFactory.getLogger(HttpUtils.class);

    /**
     * 瀹氫箟缂栫爜鏍煎紡 UTF-8
     */
    public static final String URL_PARAM_DECODECHARSET_UTF8 = "UTF-8";

    /**
     * 瀹氫箟缂栫爜鏍煎紡 GBK
     */
    public static final String URL_PARAM_DECODECHARSET_GBK = "GBK";

    private static final String URL_PARAM_CONNECT_FLAG = "&";

    private static final String EMPTY = "";

    private static MultiThreadedHttpConnectionManager connectionManager = null;

    private static int connectionTimeOut = 25000;

    private static int socketTimeOut = 25000;

    private static int maxConnectionPerHost = 20;

    private static int maxTotalConnections = 20;

    private static HttpClient client;

    static {
        connectionManager = new MultiThreadedHttpConnectionManager();
        connectionManager.getParams().setConnectionTimeout(connectionTimeOut);
        connectionManager.getParams().setSoTimeout(socketTimeOut);
        connectionManager.getParams().setDefaultMaxConnectionsPerHost(maxConnectionPerHost);
        connectionManager.getParams().setMaxTotalConnections(maxTotalConnections);
        client = new HttpClient(connectionManager);
    }

    /**
     * POST鏂瑰紡鎻愪氦鏁版嵁
     * 
     * @param url
     *            寰呰姹傜殑URL
     * @param params
     *            瑕佹彁浜ょ殑鏁版嵁
     * @param enc
     *            缂栫爜
     * @return 鍝嶅簲缁撴灉
     * @throws IOException
     *             IO寮傚父
     */
    //UTF-8
    public static String URLPost(String url, Map<String, String> params, String enc) {

        String response = EMPTY;
        PostMethod postMethod = null;
        try {
            postMethod = new PostMethod(url);
            postMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=" + enc);
            // 灏嗚〃鍗曠殑鍊兼斁鍏ostMethod涓�
            Set<String> keySet = params.keySet();
            for (String key : keySet) {
                String value = params.get(key);
                postMethod.addParameter(key, value);
            }
            // 鎵цpostMethod
            int statusCode = client.executeMethod(postMethod);
            if (statusCode == HttpStatus.SC_OK) {
                response = postMethod.getResponseBodyAsString();
            } else {
                logger.error("鍝嶅簲鐘舵�鐮�= " + postMethod.getStatusCode());
            }
        } catch (HttpException e) {
            logger.error("鍙戠敓鑷村懡鐨勫紓甯革紝鍙兘鏄崗璁笉瀵规垨鑰呰繑鍥炵殑鍐呭鏈夐棶棰�, e");
            e.printStackTrace();
        } catch (IOException e) {
            logger.error("鍙戠敓缃戠粶寮傚父", e);
            e.printStackTrace();
        } finally {
            if (postMethod != null) {
                postMethod.releaseConnection();
                postMethod = null;
            }
        }

        return response;
    }
    public static String URLPost(String url, Map<String, String> params, String enc,JSONObject requstBody) {

        String response = EMPTY;
        PostMethod postMethod = null;
        try {
            postMethod = new PostMethod(url);
            postMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=" + enc);
            RequestEntity se = new StringRequestEntity(requstBody.toJSONString(), "application/json", "UTF-8");
            postMethod.setRequestEntity(se);
            // 灏嗚〃鍗曠殑鍊兼斁鍏ostMethod涓�
            if(params!=null){
	            Set<String> keySet = params.keySet();
	            for (String key : keySet) {
	                String value = params.get(key);
	                postMethod.addParameter(key, value);
	            }
            }
            // 鎵цpostMethod
            int statusCode = client.executeMethod(postMethod);
            if (statusCode == HttpStatus.SC_OK) {
                response = postMethod.getResponseBodyAsString();
            } else {
                logger.error("鍝嶅簲鐘舵�鐮�= " + postMethod.getStatusCode());
            }
        } catch (HttpException e) {
            logger.error("鍙戠敓鑷村懡鐨勫紓甯革紝鍙兘鏄崗璁笉瀵规垨鑰呰繑鍥炵殑鍐呭鏈夐棶棰�, e");
            e.printStackTrace();
        } catch (IOException e) {
            logger.error("鍙戠敓缃戠粶寮傚父", e);
            e.printStackTrace();
        } finally {
            if (postMethod != null) {
                postMethod.releaseConnection();
                postMethod = null;
            }
        }

        return response;
    }
    /**
     * GET鏂瑰紡鎻愪氦鏁版嵁
     * 
     * @param url
     *            寰呰姹傜殑URL
     * @param params
     *            瑕佹彁浜ょ殑鏁版嵁
     * @param enc
     *            缂栫爜
     * @return 鍝嶅簲缁撴灉
     * @throws IOException
     *             IO寮傚父
     */
    public static String URLGet(String url, Map<String, String> params, String enc) {

        String response = EMPTY;
        GetMethod getMethod = null;
        StringBuffer strtTotalURL = new StringBuffer(EMPTY);
        if(params!=null){
	        if (url.indexOf("?") == -1) {
	            strtTotalURL.append(url).append("?").append(getUrl(params, enc));
	        } else {
	            strtTotalURL.append(url).append("&").append(getUrl(params, enc));
	        }
        }
        logger.debug("GET璇锋眰URL = \n" + strtTotalURL.toString());
        
        try {
            getMethod = new GetMethod(strtTotalURL.toString());
            getMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=" + enc);
            // 鎵цgetMethod
            int statusCode = client.executeMethod(getMethod);
            if (statusCode == HttpStatus.SC_OK) {
                response = getMethod.getResponseBodyAsString();
            } else {
                logger.debug("鍝嶅簲鐘舵�鐮�= " + getMethod.getStatusCode());
            }
        } catch (HttpException e) {
            logger.error("鍙戠敓鑷村懡鐨勫紓甯革紝鍙兘鏄崗璁笉瀵规垨鑰呰繑鍥炵殑鍐呭鏈夐棶棰�, e");
            e.printStackTrace();
        } catch (IOException e) {
            logger.error("鍙戠敓缃戠粶寮傚父", e);
            e.printStackTrace();
        } finally {
            if (getMethod != null) {
                getMethod.releaseConnection();
                getMethod = null;
            }
        }

        return response;
    }	
    public static boolean URLGetImg(String url, Map<String, String> params, String enc,String savePath) {
    	if(savePath==null){
    		return false;
    	}
    	GetMethod getMethod = null;
         FileOutputStream fout = null;
         StringBuffer strtTotalURL = new StringBuffer(EMPTY);
        if(params!=null){
	         if (url.indexOf("?") == -1) {
	             strtTotalURL.append(url).append("?").append(getUrl(params, enc));
	         } else {
	             strtTotalURL.append(url).append("&").append(getUrl(params, enc));
	         }
        }
        else{
        	strtTotalURL.append(url);
        }
         logger.info("GET璇锋眰URL = \n" + strtTotalURL.toString());
         
         try {
             getMethod = new GetMethod(strtTotalURL.toString());
             getMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=" + enc);      
             // 鎵цgetMethod
             client.setConnectionTimeout(3000);
             int statusCode = client.executeMethod(getMethod);
             logger.info(HttpStatus.SC_OK+"  "+url);
             if (statusCode == HttpStatus.SC_OK) {
            	 BufferedImage image = ImageIO.read(getMethod.getResponseBodyAsStream());
            	 File file=new File(savePath);
            	 if(!file.exists())
            		 file.createNewFile();     
            	 fout=new FileOutputStream(file);
            	 ImageIO.write(image, "jpg", fout);
            	 return true;
             } else {
                 logger.debug("鍝嶅簲鐘舵�鐮�= " + getMethod.getStatusCode());
                 return false;
             }
         } catch (HttpException e) {
             logger.error("鍙戠敓鑷村懡鐨勫紓甯革紝鍙兘鏄崗璁笉瀵规垨鑰呰繑鍥炵殑鍐呭鏈夐棶棰�, e");
             e.printStackTrace();
         } catch (IOException e) {
             logger.error("鍙戠敓缃戠粶寮傚父", e);
             e.printStackTrace();
         } finally {
        	 try {
        		 if(fout!=null){
        			 fout.flush();
        			 fout.close();
        		 }
			} catch (IOException e) {
				e.printStackTrace();
			}           
             if (getMethod != null) {
                 getMethod.releaseConnection();
                 getMethod = null;
             }
         }
    	return false;
    }
    /**
     * 鎹甅ap鐢熸垚URL瀛楃涓�
     * 
     * @param map
     *            Map
     * @param valueEnc
     *            URL缂栫爜
     * @return URL
     */
    private static String getUrl(Map<String, String> map, String valueEnc) {

        if (null == map || map.keySet().size() == 0) {
            return (EMPTY);
        }
        StringBuffer url = new StringBuffer();
        Set<String> keys = map.keySet();
        for (Iterator<String> it = keys.iterator(); it.hasNext();) {
            String key = it.next();
            if (map.containsKey(key)) {
                String val = map.get(key);
                String str = val != null ? val : EMPTY;
                try {
                    str = URLEncoder.encode(str, valueEnc);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                url.append(key).append("=").append(str).append(URL_PARAM_CONNECT_FLAG);
            }
        }
        String strURL = EMPTY;
        strURL = url.toString();
        if (URL_PARAM_CONNECT_FLAG.equals(EMPTY + strURL.charAt(strURL.length() - 1))) {
            strURL = strURL.substring(0, strURL.length() - 1);
        }

        return (strURL);
    }
}