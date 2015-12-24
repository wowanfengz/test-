package com.taojia.app.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.taojia.app.Bean.MyPackage;
import com.taojia.app.Bean.User;
import com.taojia.app.Daoimpl.MyPackageDaoimpl;
import com.taojia.app.Daoimpl.UserDaoimpl;
import com.taojia.app.Utils.ContextParams;
import com.taojia.rongyun.ApiHttpClient;
import com.taojia.rongyun.models.FormatType;

@Controller
public class SocialContactController {
	@Autowired
	private UserDaoimpl userDaoimpl;
	@Autowired
	private MyPackageDaoimpl myPackageDaoimpl;
	@Autowired
	private ApiHttpClient apiHttpClient;
	private String appKey = ContextParams.rongyun_appKey;
	private String appSecret = ContextParams.rongyun_appSecret;
	@RequestMapping(value="/getRongYunToken",method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
    public String getRongYunToken(HttpServletRequest request, HttpServletResponse response){
		String phoneNumber = request.getParameter("phoneNumber");
		String token = request.getParameter("token");
		User user = userDaoimpl.GetUserByPhoneNumber(phoneNumber);
		JSONObject json_obj = new JSONObject();
		if(user!=null){
			if(user.getToken().equals(token)){				
				try {
					String result = apiHttpClient.getToken(appKey, appSecret, user.getPhoneNumber(), user.getPhoneNumber(), user.getAvatar(), "json").getResult();
					JSONObject jt = JSONObject.parseObject(result);
					System.out.println(result);
					System.out.println(jt.get("token"));
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
			else{
				json_obj.put("error", "token有误!");
				System.out.println(json_obj.toJSONString());
				return json_obj.toJSONString();
			}
		}
		else{
			json_obj.put("error", "手机号有误!");
			System.out.println(json_obj.toJSONString());
			return json_obj.toJSONString();
		}
	}
	
	@RequestMapping(value="/getMyFriend",method = RequestMethod.GET)
	@ResponseBody
    public String getMyFriend(HttpServletRequest request, HttpServletResponse response){
		String phoneNumber = request.getParameter("phoneNumber");
		String token = request.getParameter("token");
		User user = userDaoimpl.GetUserByPhoneNumber(phoneNumber);
		JSONObject json_obj = new JSONObject();
		if(user!=null){
			if(user.getToken().equals(token)){
				MyPackage myPackageList[] = myPackageDaoimpl.getMyPackage(phoneNumber);
				for(int i=0;i<myPackageList.length;i++){
					User friends[] = myPackageDaoimpl.getMyPackageUser(myPackageList[i].getMypackageid()+"");
					myPackageList[i].setFriends(friends);
				}
				String ret =  JSONObject.toJSONString(myPackageList);
				System.out.println(ret);
				return ret;
			}
			else{
				json_obj.put("error", "token有误!");
				System.out.println(json_obj.toJSONString());
				return json_obj.toJSONString();
			}
		}
		else{
			json_obj.put("error", "手机号有误!");
			System.out.println(json_obj.toJSONString());
			return json_obj.toJSONString();
		}
	}
}
