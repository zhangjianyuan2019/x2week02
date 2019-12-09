package com.zhangjianyuan.redis.test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bw.zjy.common.utils.DateUtil;
import com.bw.zjy.common.utils.RandomUtil;
import com.bw.zjy.common.utils.StringUtil;
import com.zhangjianyuan.redis.entity.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-redis.xml")
public class UserTest {

	@Resource
	private RedisTemplate redisTemplate;
	
	/**
	 * @Title: JDKuserTest 
	 * @Description: 测试JDK格式
	 * @return: void
	 */
	@Test
	public void JDKuserTest() {
		//创建readis模板对象
		ListOperations opsForList = redisTemplate.opsForList();
		
		//记录开始时间
		long t1 = System.currentTimeMillis();
		
		//创建数据 并 存入集合
		for (int i = 1; i <= 50000; i++) {
			//姓名使用3个随机汉字模拟
			String name = StringUtil.randomChineseString(3);
			//性别在女和男两个值中随机
			char sex = RandomUtil.randomCharacter2();
			//手机以13开头+9位随机数模拟
			String phone = "13" + RandomUtil.randomString(9);
			//邮箱以3-20个随机字母 + @qq.com  | @163.com | @sian.com | @gmail.com | @sohu.com | @hotmail.com | @foxmail.com模拟
//			for (int j = 0; j < RandomUtil.random(3, 20); j++) {
//				ema += String.valueOf(RandomUtil.randomCharacter3());
//			}
			String[] str1 = {"q","w","e","r","t","y","u","i","o","p","l","k","j","h","g","f","d","s","a","z","x","c","v","b","n","m"};
			String[] str2 = {"@qq.com","@163.com","@sian.com","@gmail.com","@sohu.com","@hotmail.com","@foxmail.com"};
			String email = str1[RandomUtil.random(0, str1.length -1)] + str2[RandomUtil.random(0, str2.length -1)];
			//生日要模拟18-70岁之间，即日期从1949年到2001年之间
			Calendar c = Calendar.getInstance();
			Date d1 = new Date(1949, 1, 1);
			Date d2 = new Date(2001, 1, 1);
			Date birthday = DateUtil.randomDate(d1, d2);
			User user = new User(i, name, sex, phone, email, birthday);
			System.out.println(i);
			//list.add(user);
			//存入redis, 使用list的方式
			opsForList.leftPush("user_list_jdk", user);
		}
		
		//记录结束时间
		long t2 = System.currentTimeMillis();
		
		System.out.println("本次储存共用时 : " + (t2 - t1));
	}
	//38817
	//40158
	
	/**
	 * @Title: JSONuserTest 
	 * @Description: 测试JSON格式
	 * @return: void
	 */
	@Test
	public void JSONuserTest() {
		//创建readis模板对象
		ListOperations opsForList = redisTemplate.opsForList();
		
		//记录开始时间
		long t1 = System.currentTimeMillis();
		
		//创建数据 并 存入集合
		for (int i = 1; i <= 50000; i++) {
			//姓名使用3个随机汉字模拟
			String name = StringUtil.randomChineseString(3);
			//性别在女和男两个值中随机
			char sex = RandomUtil.randomCharacter2();
			//手机以13开头+9位随机数模拟
			String phone = "13" + RandomUtil.randomString(9);
			//邮箱以3-20个随机字母 + @qq.com  | @163.com | @sian.com | @gmail.com | @sohu.com | @hotmail.com | @foxmail.com模拟
			String[] str1 = {"q","w","e","r","t","y","u","i","o","p","l","k","j","h","g","f","d","s","a","z","x","c","v","b","n","m"};
			String[] str2 = {"@qq.com","@163.com","@sian.com","@gmail.com","@sohu.com","@hotmail.com","@foxmail.com"};
			String email = str1[RandomUtil.random(0, str1.length -1)] + str2[RandomUtil.random(0, str2.length -1)];
			//生日要模拟18-70岁之间，即日期从1949年到2001年之间
			Calendar c = Calendar.getInstance();
			Date d1 = new Date(1949, 1, 1);
			Date d2 = new Date(2001, 1, 1);
			Date birthday = DateUtil.randomDate(d1, d2);
			User user = new User(i, name, sex, phone, email, birthday);
			System.out.println(i);
			//存入redis, 使用list的方式
			opsForList.leftPush("user_list_json", user);
		}
		//记录结束时间
		long t2 = System.currentTimeMillis();
		System.out.println("本次储存共用时 : " + (t2 - t1));
	}
	//38795
	//38602
	
	/**
	 * @Title: hashuserTest 
	 * @Description: 测试hash格式
	 * @return: void
	 */
	@Test
	public void hashuserTest() {
		//创建readis模板对象
		HashOperations opsForHash = redisTemplate.opsForHash();
		
		Map<String, User> map = new HashMap<String, User>();
		
		//记录开始时间
		long t1 = System.currentTimeMillis();
		
		//创建数据 并 存入集合
		for (int i = 1; i <= 50000; i++) {
			//姓名使用3个随机汉字模拟
			String name = StringUtil.randomChineseString(3);
			//性别在女和男两个值中随机
			char sex = RandomUtil.randomCharacter2();
			//手机以13开头+9位随机数模拟
			String phone = "13" + RandomUtil.randomString(9);
			//邮箱以3-20个随机字母 + @qq.com  | @163.com | @sian.com | @gmail.com | @sohu.com | @hotmail.com | @foxmail.com模拟
			String[] str1 = {"q","w","e","r","t","y","u","i","o","p","l","k","j","h","g","f","d","s","a","z","x","c","v","b","n","m"};
			String[] str2 = {"@qq.com","@163.com","@sian.com","@gmail.com","@sohu.com","@hotmail.com","@foxmail.com"};
			String email = str1[RandomUtil.random(0, str1.length -1)] + str2[RandomUtil.random(0, str2.length -1)];
			//生日要模拟18-70岁之间，即日期从1949年到2001年之间
			Calendar c = Calendar.getInstance();
			Date d1 = new Date(1949, 1, 1);
			Date d2 = new Date(2001, 1, 1);
			Date birthday = DateUtil.randomDate(d1, d2);
			User user = new User(i, name, sex, phone, email, birthday);
			System.out.println(i);
			map.put("user_hash", user);
			//存入redis, 使用hash的方式
			opsForHash.put("user_hash", "user_hash", map);
		}
		//记录结束时间
		long t2 = System.currentTimeMillis();
		System.out.println("本次储存共用时 : " + (t2 - t1));
	}
	//39772
	//39568
	
}
