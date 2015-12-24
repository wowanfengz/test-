package com.taojia.app.Controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.taojia.app.Bean.FeedBack;
import com.taojia.app.Bean.User;
import com.taojia.app.Daoimpl.CouponsDaoimpl;
import com.taojia.app.Daoimpl.FeedBackDaoimpl;
import com.taojia.app.Daoimpl.UserDaoimpl;
import com.taojia.app.Utils.ContextParams;
import com.taojia.app.Utils.MD5Util;
import com.taojia.rongyun.ApiHttpClient;

@Controller
public class UserInfoController {
	SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
	@Autowired
	private UserDaoimpl userDaoimpl;
	@Autowired
	private FeedBackDaoimpl feedBackDaoimpl;
	@Autowired
	private CouponsDaoimpl couponsDaoimpl;
	@Autowired
	private ApiHttpClient apiHttpClient;
	private String appKey = ContextParams.rongyun_appKey;
	private String appSecret = ContextParams.rongyun_appSecret;
	@RequestMapping(value="/register",method = RequestMethod.GET)
	@ResponseBody
    public String resign(HttpServletRequest request, HttpServletResponse response){
    	String username = request.getParameter("phoneNumber");
		String password = request.getParameter("password");
		String cofi_password = request.getParameter("cofi_password");
		String phoneNumber = request.getParameter("phoneNumber");    		
		Date nowDate = null;
	    String nowTime = format.format(new Date());    
        try {
        	nowDate = format.parse(nowTime);
		} catch (ParseException e) {			
			e.printStackTrace();
		} 
        UserDaoimpl userDaoimpl = new UserDaoimpl();
		User otherUser = userDaoimpl.GetUserByPhoneNumber(phoneNumber);
		JSONObject json_obj = new JSONObject();
		if(otherUser!=null){
			json_obj.put("status", -1);
			return json_obj.toString();
		}
		if(!password.equals(cofi_password)){
			json_obj.put("status", -2);
			return json_obj.toString();
		}  
		password = MD5Util.MD5(password);
		User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setPhoneNumber(phoneNumber);
        user.setRegisterTime(nowDate.getTime());
        
        //获取融云token
        try {
			apiHttpClient.getToken(appKey, appSecret, user.getPhoneNumber(), user.getPhoneNumber(), user.getAvatar(), "json");
			String result = apiHttpClient.getToken(appKey, appSecret, user.getPhoneNumber(), user.getPhoneNumber(), user.getAvatar(), "json").getResult();
			JSONObject jt = JSONObject.parseObject(result);
			user.setRongYunToken(jt.getString("token"));
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        int num = userDaoimpl.addUser(user);    		    		
        if(num!=0){
			json_obj.put("status", 1);
        	return json_obj.toString();
        }
        else{
			json_obj.put("status", -4);
        	return json_obj.toString();
        }
    }
	@RequestMapping(value="/login",method = RequestMethod.GET)
	@ResponseBody
	public String login(HttpServletRequest request, HttpServletResponse response){	
		String phoneNumber = request.getParameter("phoneNumber");
    	String password = request.getParameter("password");		
    	Date nowDate = null;
        String nowTime = format.format(new Date()); 
    	try {
         	nowDate = format.parse(nowTime);
 		} catch (ParseException e) {			
 			e.printStackTrace();
 		} 
		User user = userDaoimpl.GetUserByPhoneNumber(phoneNumber);				
		JSONObject json_obj = new JSONObject();
		if(user==null){			
			json_obj.put("status", -1);
			json_obj.put("user", null);
			return json_obj.toString();
		}
		password = MD5Util.MD5(password);
		if(password.equals(user.getPassword())){			
			Random random = new Random();
			int random_dig = random.nextInt(100000000);
			String token = MD5Util.MD5(random_dig+"");
			user.setToken(token);
			user.setLoginTime(nowDate.getTime());
			user.setCoupons_list(couponsDaoimpl.getCouponsByUserid(user.getUserid(), 0, 1000));
			if(user.getRongYunToken()==null){
				//获取融云token
		        try {
					apiHttpClient.getToken(appKey, appSecret, user.getPhoneNumber(), user.getPhoneNumber(), user.getAvatar(), "json");
					String result = apiHttpClient.getToken(appKey, appSecret, user.getPhoneNumber(), user.getPhoneNumber(), user.getAvatar(), "json").getResult();
					JSONObject jt = JSONObject.parseObject(result);
					user.setRongYunToken(jt.getString("token"));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			int isUpdate = userDaoimpl.updateUserLoginStatus(user);
			if(isUpdate==1){
				json_obj.put("status", 1);
				json_obj.put("user", user);		
				
			}
			else{
				json_obj.put("status", -4);
				json_obj.put("user", user);
			}
			return json_obj.toString();
		}
		else{
			json_obj.put("status", -2);
			json_obj.put("user", null);
			return json_obj.toString();
		}
	}
	
	@RequestMapping(value="/getUserInfo",method = RequestMethod.GET)
	@ResponseBody
	public String getUserInfo(HttpServletRequest request, HttpServletResponse response){	
		String phoneNumber = request.getParameter("phoneNumber");
		String token = request.getParameter("token");		
		User user = userDaoimpl.GetUserByPhoneNumber(phoneNumber);
		JSONObject json_obj = new JSONObject();
		if(user!=null){
			if(user.getToken().equals(token)){
				json_obj.put("user", user);
			}
			else{
				json_obj.put("user", null);
			}
		}
		else{
			json_obj.put("user", null);
		}
		return json_obj.toString();
	}
	@RequestMapping(value="/updatePassword",method = RequestMethod.GET)
	@ResponseBody
	public String updatePassword(HttpServletRequest request, HttpServletResponse response){	
		String phoneNumber = request.getParameter("phoneNumber");
		String password = request.getParameter("password");
		String cofi_password= request.getParameter("cofi_password");
		JSONObject json_obj = new JSONObject();
		if(!password.equals(cofi_password)){
			json_obj.put("status", -2);
			json_obj.put("user", null);
			return json_obj.toString();
		}
		User user = userDaoimpl.GetUserByPhoneNumber(phoneNumber);
		
		if(user!=null){			
			password = MD5Util.MD5(password);
			user.setPassword(password);
			int num = userDaoimpl.updatePassword(user);
			if(num==1){
				json_obj.put("user", user);
				json_obj.put("status", 1);
			}
			else{
				json_obj.put("user", null);
				json_obj.put("status", -4);
			}			
		}
		else{
			json_obj.put("status",-3);
			json_obj.put("user", null);
		}
		return json_obj.toString();
	}
	
	@RequestMapping(value="/updateUserAvatar",method = RequestMethod.POST)
	@ResponseBody
	public String updateUserAvatar(HttpServletRequest request, HttpServletResponse response){
		File tmpDir = new File("D:/apache-tomcat-7.0.64/webapps/TaoJia/resources/tmp/");// ��ʼ���ϴ��ļ�����ʱ���Ŀ¼
	    String saveDir = "D:/apache-tomcat-7.0.64/webapps/TaoJia/resources/images/";// ��ʼ���ϴ��ļ���ı���Ŀ¼
		String phoneNumber = "";
		String token = "";
		BufferedInputStream in = null;
        BufferedOutputStream out = null;
        List fileList = null; 
        JSONObject json_obj = new JSONObject();
        FileItem fileUp = null;
        String fileName = null;
		try {
            if (ServletFileUpload.isMultipartContent(request)) {
                DiskFileItemFactory dff = new DiskFileItemFactory();// �����ö���
                dff.setRepository(tmpDir);// ָ���ϴ��ļ�����ʱĿ¼
                dff.setSizeThreshold(1024000);// ָ�����ڴ��л�����ݴ�С,��λΪbyte
                ServletFileUpload sfu = new ServletFileUpload(dff);// �����ö���
                sfu.setFileSizeMax(5000000);// ָ�������ϴ��ļ������ߴ�
                sfu.setSizeMax(10000000);// ָ��һ���ϴ�����ļ����ܳߴ�
                fileList = sfu.parseRequest(request);// ����request	  
                Iterator fii = fileList.iterator();
                while (fii.hasNext()) {     
                	FileItem fileItem = (FileItem) fii.next();
                	if(fileItem == null || fileItem.isFormField()){
                		String formname=fileItem.getFieldName();//��ȡform�е�����  
                		String formcontent = fileItem.getString();
                		if(formname.equals("phoneNumber")){
                			phoneNumber = formcontent; 
                		}
                		else if(formname.equals("token")){
                            token = formcontent;
                		}
                        System.out.println(formname+" "+formcontent);
                	}         
                	else if (!fileItem.isFormField() && fileItem.getName().length() > 0) {// ���˵��?�з��ļ���		
                		fileUp =  fileItem;
                    	String path = fileUp.getName();
                    	fileName = path.substring(path.lastIndexOf("\\") + 1);
                    }
                }                
            }
        }catch (Exception e) {
        	json_obj.put("user", null);			
            e.printStackTrace();
            return json_obj.toString();
        }
		System.out.println(phoneNumber+"   "+token);
		User user = userDaoimpl.GetUserByPhoneNumber(phoneNumber);
		long now = System.currentTimeMillis();  
        // ���ϵͳʱ������ϴ��󱣴���ļ���  
        String prefix = String.valueOf(now);
		if(user!=null){
			if(user.getToken().equals(token)){
				try {
					user.setAvatar("/resources/images/"+prefix+"_"+fileName);
					int num = userDaoimpl.updateUser(user);					
					fileUp.write(new File(saveDir+prefix+"_"+fileName)); 
					json_obj.put("user", user);
				} catch (Exception e) {
					json_obj.put("user", null);				
					e.printStackTrace();
					return json_obj.toString();
				}				
			}
			else{
				json_obj.put("user", null);
			}
		}
		else{
			json_obj.put("user", null);
		}
		return json_obj.toString();
	}
	@RequestMapping(value="/getMyOrder",method = RequestMethod.GET)
	@ResponseBody
	public String getMyOrder(HttpServletRequest request, HttpServletResponse response){
		String content = null;
		String phoneNumber = request.getParameter("phoneNumber");
		String token = request.getParameter("token");
		User user = userDaoimpl.GetUserByPhoneNumber(phoneNumber);
		JSONObject json_obj = new JSONObject();
		if(user!=null){
			if(user.getToken().equals(token)){
				
			}
			else{
				json_obj.put("status", -2);
			}
		}
		else{
			json_obj.put("status", -3);
		}
		return json_obj.toString();
	}
	@RequestMapping(value="/feedBack",method = RequestMethod.POST)
	@ResponseBody
	public String feedBack(HttpServletRequest request, HttpServletResponse response){
		String content = null;
		String phoneNumber = request.getParameter("phoneNumber");
		String token = request.getParameter("token");
		if(request.getParameter("content")!=null){
			content = request.getParameter("content");
		}
		User user = userDaoimpl.GetUserByPhoneNumber(phoneNumber);
		JSONObject json_obj = new JSONObject();
		if(user!=null){
			if(user.getToken().equals(token)){
				long time = System.currentTimeMillis();
				FeedBack feedBack = new FeedBack();
				feedBack.setContent(content);
				feedBack.setTime(time);
				feedBack.setUserid(user.getUserid());
				int result = feedBackDaoimpl.insertFeedBack(feedBack);
				if(result==0){
					json_obj.put("status", -1);
				}
				else{
					json_obj.put("status", 1);
				}
			}
			else{
				json_obj.put("status", -2);
			}
		}
		else{
			json_obj.put("status", -3);
		}
		return json_obj.toString();
	}
}
