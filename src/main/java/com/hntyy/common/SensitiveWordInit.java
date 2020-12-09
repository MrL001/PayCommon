package com.hntyy.common;

import com.hntyy.entity.mjjzxyh.SensitiveWordsEntity;
import com.hntyy.entity.mjjzxyh.SensitiveWordsQuery;
import com.hntyy.service.mjjzxyh.SensitiveWordsService;
import com.hntyy.service.mjjzxyh.SensitiveWordsServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @Description: 初始化敏感词库，将敏感词加入到HashMap中，构建DFA算法模型
 * @version 1.0
 */
@Slf4j
public class SensitiveWordInit {

	private String ENCODING = "UTF-8";    //字符编码
	public HashMap sensitiveWordMap;
	
	public SensitiveWordInit(){
		super();
	}
	
	/**
	 * @version 1.0
	 */
	public Map initKeyWord(RedisUtil redisUtil,SensitiveWordsService sensitiveWordsService){
		try {
			//读取敏感词库
			List<SensitiveWordsEntity> all = (List<SensitiveWordsEntity>) redisUtil.get("sensitiveWords");
			if (CollectionUtils.isEmpty(all)){
				all = sensitiveWordsService.findAllSensitiveWords();
				boolean sensitiveWords = redisUtil.set("sensitiveWords", all);
				log.info("敏感词保存结果："+sensitiveWords);
			}
			Set<String> keyWordSet = new HashSet<>();
			all.stream().forEach(sensitiveWordsEntity -> keyWordSet.add(sensitiveWordsEntity.getWord()));
			//将敏感词库加入到HashMap中
			addSensitiveWordToHashMap(keyWordSet);
			//spring获取application，然后application.setAttribute("sensitiveWordMap",sensitiveWordMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sensitiveWordMap;
	}

	/**
	 * 读取敏感词库，将敏感词放入HashSet中，构建一个DFA算法模型：<br>
	 * 中 = {
	 *      isEnd = 0
	 *      国 = {<br>
	 *      	 isEnd = 1
	 *           人 = {isEnd = 0
	 *                民 = {isEnd = 1}
	 *                }
	 *           男  = {
	 *           	   isEnd = 0
	 *           		人 = {
	 *           			 isEnd = 1
	 *           			}
	 *           	}
	 *           }
	 *      }
	 *  五 = {
	 *      isEnd = 0
	 *      星 = {
	 *      	isEnd = 0
	 *      	红 = {
	 *              isEnd = 0
	 *              旗 = {
	 *                   isEnd = 1
	 *                  }
	 *              }
	 *      	}
	 *      }
	 * @param keyWordSet  敏感词库
	 * @version 1.0
	 */
	private void addSensitiveWordToHashMap(Set<String> keyWordSet) {
		sensitiveWordMap = new HashMap(keyWordSet.size());     //初始化敏感词容器，减少扩容操作
		String key = null;  
		Map nowMap = null;
		Map<String, String> newWorMap = null;
		//迭代keyWordSet
		Iterator<String> iterator = keyWordSet.iterator();
		while(iterator.hasNext()){
			key = iterator.next();    //关键字
			nowMap = sensitiveWordMap;
			for(int i = 0 ; i < key.length() ; i++){
				char keyChar = key.charAt(i);       //转换成char型
				Object wordMap = nowMap.get(keyChar);       //获取
				
				if(wordMap != null){        //如果存在该key，直接赋值
					nowMap = (Map) wordMap;
				}
				else{     //不存在则，则构建一个map，同时将isEnd设置为0，因为他不是最后一个
					newWorMap = new HashMap<String,String>();
					newWorMap.put("isEnd", "0");     //不是最后一个
					nowMap.put(keyChar, newWorMap);
					nowMap = newWorMap;
				}
				
				if(i == key.length() - 1){
					nowMap.put("isEnd", "1");    //最后一个
				}
			}
		}
	}

	/**
	 * 读取敏感词库中的内容，将内容添加到set集合中
	 * @return
	 * @version 1.0
	 * @throws Exception 
	 */
	private Set<String> readSensitiveWordFile() throws Exception{
		Set<String> set = new HashSet<>();
		// 读取数据库敏感词
		SensitiveWordsServiceImpl sensitiveWordsService = (SensitiveWordsServiceImpl)SpringUtil.getBean(SensitiveWordsServiceImpl.class);
		List<SensitiveWordsEntity> all = sensitiveWordsService.findAll(new SensitiveWordsQuery());
		all.stream().forEach(sensitiveWordsEntity -> set.add(sensitiveWordsEntity.getWord()));
		return set;
	}
}
