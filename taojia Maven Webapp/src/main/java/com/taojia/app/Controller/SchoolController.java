package com.taojia.app.Controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.taojia.app.Bean.Camp;
import com.taojia.app.Bean.Coach;
import com.taojia.app.Bean.CoachComment;
import com.taojia.app.Bean.Coupons;
import com.taojia.app.Bean.School;
import com.taojia.app.Bean.SchoolComment;
import com.taojia.app.Bean.User;
import com.taojia.app.Bean.UserOrder;
import com.taojia.app.Daoimpl.CampDaoimpl;
import com.taojia.app.Daoimpl.CoachCommentDaoimpl;
import com.taojia.app.Daoimpl.CoachDaoimpl;
import com.taojia.app.Daoimpl.CouponsDaoimpl;
import com.taojia.app.Daoimpl.SchoolCommentDaoimpl;
import com.taojia.app.Daoimpl.SchoolDaoimpl;
import com.taojia.app.Daoimpl.UserDaoimpl;
import com.taojia.app.Daoimpl.UserOrderDaoimpl;

@Controller
public class SchoolController {
	@Autowired
	private SchoolDaoimpl schoolDaoimpl ;
	@Autowired
	private SchoolCommentDaoimpl schoolCommentDaoimpl ;
	@Autowired
	private CoachDaoimpl coachDaoimpl;
	@Autowired
	private CoachCommentDaoimpl coachCommentDaoimpl;
	@Autowired
	private UserDaoimpl userDaoimpl;
	@Autowired
	private CouponsDaoimpl couponsDaoimpl ;
	@Autowired
	private CampDaoimpl campDaoimpl;
	@RequestMapping(value="/upload",method = RequestMethod.GET)
	public String upload(){
		return "upload";
	}
	@RequestMapping(value="/alipay",method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String alipay(HttpServletRequest request){
		String result = request.getParameter("out_trade_no");
		System.out.println(result);
		return "success";
	}
	@RequestMapping(value="/getSchool",method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getSchool(HttpServletRequest request, HttpServletResponse response){
		int page=0,count=5,comm_page=0,comm_count=5;
		if(request.getParameter("page")!=null){
			page = Integer.parseInt(request.getParameter("page"));
		}
		if(request.getParameter("count")!=null){
			count = Integer.parseInt(request.getParameter("count"));
		}
		if(request.getParameter("comm_page")!=null){
			page = Integer.parseInt(request.getParameter("comm_page"));
		}
		if(request.getParameter("comm_count")!=null){
			count = Integer.parseInt(request.getParameter("comm_count"));
		}		
		JSONObject json_obj = new JSONObject();

		School school_list[] = schoolDaoimpl.getSchool(page, count);
		for(int i=0;i<school_list.length;i++){
			String img_list[] = schoolDaoimpl.getSchoolEnvironmental(school_list[i].getSchoolid());
			school_list[i].setEnvironmental_img_list(img_list);
			SchoolComment[] schoolComments = schoolCommentDaoimpl.getSchoolCommentBySchoolid(school_list[i].getSchoolid(), comm_page, comm_count);
			school_list[i].setSchoolComments(schoolComments);
		}
		json_obj.put("school_list", school_list);
		return json_obj.toString();
	}
	@RequestMapping(value="/getCoachBySchoolid",method = RequestMethod.GET,produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getCoachBySchoolid(HttpServletRequest request, HttpServletResponse response){
		int page=0,count=5,comm_page=0,comm_count=5;
		int schoolid= -1;
		if(request.getParameter("schoolid")!=null){
			schoolid = Integer.parseInt(request.getParameter("schoolid"));
		}
		if(request.getParameter("page")!=null){
			page = Integer.parseInt(request.getParameter("page"));
		}
		if(request.getParameter("count")!=null){
			count = Integer.parseInt(request.getParameter("count"));
		}
		if(request.getParameter("comm_page")!=null){
			page = Integer.parseInt(request.getParameter("comm_page"));
		}
		if(request.getParameter("comm_count")!=null){
			count = Integer.parseInt(request.getParameter("comm_count"));
		}
		
		JSONObject json_obj = new JSONObject();
		Coach coach_list[] = coachDaoimpl.getCoachBySchoolid(schoolid,page, count);
		for(int i=0;i<coach_list.length;i++){
			CoachComment[] coachComments = coachCommentDaoimpl.getCoachComment(coach_list[i].getCoachid(), comm_page, comm_count);
			coach_list[i].setCoachComments(coachComments);
		}
		json_obj.put("coach_list", coach_list);				
		return json_obj.toString();
	}
	@RequestMapping(value="/getCoach",method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getCoach(HttpServletRequest request, HttpServletResponse response){
		int page=0,count=5,comm_page=0,comm_count=5;
		if(request.getParameter("page")!=null){
			page = Integer.parseInt(request.getParameter("page"));
		}
		if(request.getParameter("count")!=null){
			count = Integer.parseInt(request.getParameter("count"));
		}
		if(request.getParameter("comm_page")!=null){
			page = Integer.parseInt(request.getParameter("comm_page"));
		}
		if(request.getParameter("comm_count")!=null){
			count = Integer.parseInt(request.getParameter("comm_count"));
		}
		
		JSONObject json_obj = new JSONObject();
		Coach coach_list[] = coachDaoimpl.getCoach(page, count);
		for(int i=0;i<coach_list.length;i++){
			CoachComment[] coachComments = coachCommentDaoimpl.getCoachComment(coach_list[i].getCoachid(), comm_page, comm_count);
			coach_list[i].setCoachComments(coachComments);
		}

		json_obj.put("coach_list", coach_list);
		System.out.println( json_obj.toString());
		return json_obj.toString();
	}
	
	@RequestMapping(value="/getCoachComment",method = RequestMethod.GET,produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getCoachComment(HttpServletRequest request, HttpServletResponse response){
		int page=0,count=5,comm_page=0,comm_count=5;
		int coachid= -1;
		if(request.getParameter("coachid")!=null){
			coachid = Integer.parseInt(request.getParameter("coachid"));
		}
		if(request.getParameter("comm_page")!=null){
			page = Integer.parseInt(request.getParameter("comm_page"));
		}
		if(request.getParameter("comm_count")!=null){
			count = Integer.parseInt(request.getParameter("comm_count"));
		}	
		JSONObject json_obj = new JSONObject();	
	    CoachComment[] coachComments = coachCommentDaoimpl.getCoachComment(coachid, comm_page, comm_count);			
		json_obj.put("coachComments", coachComments);			
		return json_obj.toString();
	}
	
	@RequestMapping(value="/getSchoolComment",method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getSchoolComment(HttpServletRequest request, HttpServletResponse response){
		int page=0,count=5,comm_page=0,comm_count=5;
		int schoolid= -1;
		if(request.getParameter("schoolid")!=null){
			schoolid = Integer.parseInt(request.getParameter("schoolid"));
		}
		if(request.getParameter("comm_page")!=null){
			page = Integer.parseInt(request.getParameter("comm_page"));
		}
		if(request.getParameter("comm_count")!=null){
			count = Integer.parseInt(request.getParameter("comm_count"));
		}
		JSONObject json_obj = new JSONObject();
		SchoolComment[] schoolComments = schoolCommentDaoimpl.getSchoolCommentBySchoolid(schoolid, comm_page, comm_count);				
		json_obj.put("schoolComments", schoolComments);				
		return json_obj.toString();
	}
	@RequestMapping(value="/submitOrder",method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String submitOrder(HttpServletRequest request, HttpServletResponse response){
		
		long coachid = Integer.parseInt(request.getParameter("coachid"));
		String token = request.getParameter("token");
		int paytype = Integer.parseInt(request.getParameter("paytype"));
		double price = Double.parseDouble(request.getParameter("price"));
		long couponsid = Integer.parseInt(request.getParameter("couponsid"));		
		String phoneNumber = request.getParameter("phoneNumber");
		int isDone = Integer.parseInt(request.getParameter("isDone"));
		
		SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
		
		System.out.println(coachid+" "+token+" "+paytype+" "+price+" "+couponsid+" "+phoneNumber+" "+isDone+" "+couponsid);
		Date nowDate = null;
        String nowTime = format.format(new Date()); 
    	try {
         	nowDate = format.parse(nowTime);
 		} catch (ParseException e) {			
 			e.printStackTrace();
 		} 
    	User user = userDaoimpl.GetUserByPhoneNumber(phoneNumber);
		JSONObject json_obj = new JSONObject();
		if(user!=null){
			if(user.getToken().equals(token)){
				UserOrder order = new UserOrder();
				order.setPaytype(paytype);
				order.setPrice(price);
				order.setTime(nowDate.getTime());
				order.setUserid(user.getUserid());
				order.setCoachid(coachid);
				order.setCouponsid(couponsid);
				order.setIsDone(isDone);
				UserOrderDaoimpl orderDaoimpl = new UserOrderDaoimpl();
				int num = orderDaoimpl.submitOrder(order);
				if(num!=0){
					json_obj.put("status",1);
					return json_obj.toString();
				}
			}
			else{
				json_obj.put("status", -1);
				return json_obj.toString();
			}
		}
		else{
			json_obj.put("status", -2);
			return json_obj.toString();
		}			
		json_obj.put("status", -3);				
		return json_obj.toString();
	}
	
	@RequestMapping(value="/getCoupons",method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getCoupons(HttpServletRequest request, HttpServletResponse response){
		
		int page=0,count=5;
		int schoolid= -1;
		String phoneNumber = request.getParameter("phoneNumber");
		String token = request.getParameter("token");
		long userid = Integer.parseInt(request.getParameter("userid"));
		if(request.getParameter("page")!=null){
			page = Integer.parseInt(request.getParameter("page"));
		}
		if(request.getParameter("count")!=null){
			count = Integer.parseInt(request.getParameter("count"));
		}		
    	User user = userDaoimpl.GetUserByPhoneNumber(phoneNumber);
		JSONObject json_obj = new JSONObject();
		if(user!=null){
			if(user.getToken().equals(token)){
				Coupons[] coupons_list = couponsDaoimpl.getCouponsByUserid(userid, page, count);
				long nowTime = System.currentTimeMillis();
				for(int i=0;i<coupons_list.length;i++){
					if((nowTime-coupons_list[i].getEndTime())>0){
						coupons_list[i].setIsDone(0);
					}
					else{
						coupons_list[i].setIsDone(1);
					}
				}
				json_obj.put("coupons_list", coupons_list);
			}
			else{
				json_obj.put("coupons_list",null);		
			}
		}
		else{
			json_obj.put("coupons_list",null);		
		}
		return json_obj.toString();
	}
	@RequestMapping(value="/getCamp",method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getCamp(HttpServletRequest request){
		Camp[] camps = campDaoimpl.getCamp();
		JSONObject json_obj = new JSONObject();
		if(camps!=null){
			for(int i=0;i<camps.length;i++){
				Coach[] coachs = campDaoimpl.getCampCoach(camps[i].getCampid());
				User[] users = campDaoimpl.getCampUser(camps[i].getCampid());
				camps[i].setCoachs(coachs);
				camps[i].setUsers(users);
				camps[i].setCoach_count(coachs.length);
				camps[i].setNow_people_sum(users.length);
			}
			json_obj.put("camps", camps);
		}
		else{
			json_obj.put("camps", null);
		}
		return json_obj.toString();
	}
}
