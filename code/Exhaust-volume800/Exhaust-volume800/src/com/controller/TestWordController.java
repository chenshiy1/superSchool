package com.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.entity.Chapter;
import com.entity.Exam;
import com.entity.Question;
import com.entity.QuestionType;
import com.entity.Sort;
import com.service.ChapterService;
import com.service.TestWordService;
import com.tool.WordGenerator;

@Controller
@RequestMapping("test")
public class TestWordController {
	@Resource
	private TestWordService testService;
	
	@RequestMapping("Word")
	public void MakeWord(HttpServletRequest request,HttpServletResponse response,
			HttpSession session){
		 try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}  
		
		 

		 	Exam exam = (Exam)session.getAttribute("exam");
	    	Set set = exam.getSorts();
	    	//各题型每小题的分值
	    	List<Integer> listPointValues=new ArrayList();
	    	for(int l=0;l<10;l++){
	    		listPointValues.add(0);
	    	}
	    	Map<Integer,Integer> map2=new HashMap();
	    	Iterator i=set.iterator();
	    	while(i.hasNext()){
	    		Sort s=(Sort) i.next();
	    		map2.put(s.getType().getId(), s.getPointvalue());
	    		listPointValues.set(s.getType().getId()-1,s.getPointvalue());
	    		
	    	}
	    	
	    	System.out.println("map2键为0的值是："+map2.get(0));
	    	System.out.println("map2键为1的值是："+map2.get(2));
	    	System.out.println("map2键为2的值是："+map2.get(2));
	    	System.out.println("map2键为3的值是："+map2.get(3));
	    	int sum=0;	    	
	    	List listpointvalue = this.testService.getScore(map2,exam);

	    	//各题型的个数
	    	List<Integer> listNum=new ArrayList();
	    	for(int l=0;l<10;l++){
	    		listNum.add(0);
	    	}
	    	Iterator y=set.iterator();
	    	while(y.hasNext()){
	    		Sort s=(Sort) y.next();
	    		int t = listNum.get(s.getType().getId()-1);
	    		listNum.set(s.getType().getId()-1,++t);
	    	}
	    	
	    	
	    	List<Integer> listSumValue=new ArrayList();
	    	//各题型的总分
	    	for(int l=0;l<10;l++){
	    		listSumValue.add(0);
	    	}
	    	for(int l=0;l<10;l++){
	    		listSumValue.set(l, listNum.get(l)*listPointValues.get(l));
	    	}
	    
		
//		 	List <QuestionType>questionList = (List<QuestionType>) session.getAttribute("types");
		 	List<HashMap<QuestionType,Integer>> types = (List<HashMap<QuestionType, Integer>>) session.getAttribute("types");
		 	Map<String, Object> map = new HashMap<String, Object>(); 
		 	List <Question>listone = new ArrayList();
		 	List <Question>listtow = new ArrayList();
		 	List <Question>listthree = new ArrayList();
		 	List <Question>listfour = new ArrayList();
		 	List <Question>listfive = new ArrayList();
		 	List <Question>listsix = new ArrayList();
		 	List <Question>listserve = new ArrayList();
		 	List <Question>listeight = new ArrayList();
		 	List <Question>listnine = new ArrayList();
		 	List <Question>listten = new ArrayList();
		 	Set<Sort>sorts = exam.getSorts();
		 	
		 	Iterator iterator = sorts.iterator();
		 	while(iterator.hasNext()){
		 		Sort sort = (Sort)iterator.next();
		 		//如果是题型一的题，将试题按照序号放到list集合中
		 		if(sort.getType().getId() == 1){
		 			int size = listone.size();
		 			int sequence = sort.getSequence();
		 			Question q = null;
		 			if(size>=sequence){
		 				Question question = sort.getQuestion();
		 				String content = question.getContent();
		 				while(content.contains("<")){
		 					String sub=content.substring(content.indexOf("<"), content.indexOf(">")+1);
		 					content=content.replace(sub,"");
		 				}
		 				content=content.trim();
		 				content=content.replace("\r\n","<w:br/>");
		 				question.setContent(content);
		 				String A = question.getA();
		 				while(A.contains("<")){
		 					String sub=A.substring(A.indexOf("<"), A.indexOf(">")+1);
		 					A=A.replace(sub,"");
		 				}
		 				A=A.trim();
		 				A=A.replace("\r\n","<w:br/>");
		 				question.setA(A);
		 				
		 				String B= question.getB();
		 				while(B.contains("<")){
		 					String sub=B.substring(B.indexOf("<"), B.indexOf(">")+1);
		 					B=B.replace(sub,"");
		 				}
		 				B=B.trim();
		 				B=B.replace("\r\n","<w:br/>");
		 				question.setB(B);
		 				
		 				String C = question.getC();
		 				while(C.contains("<")){
		 					String sub=C.substring(C.indexOf("<"), C.indexOf(">")+1);
		 					C=C.replace(sub,"");
		 				}
		 				C=C.trim();
		 				C=C.replace("\r\n","<w:br/>");
		 				question.setC(C);
		 				
		 				String D= question.getD();
		 				while(D.contains("<")){
		 					String sub=D.substring(D.indexOf("<"), D.indexOf(">")+1);
		 					D=D.replace(sub,"");
		 				}
		 				D=D.trim();
		 				D=D.replace("\r\n","<w:br/>");
		 				question.setD(D);
		 				
		 				listone.set(sequence-1,question);
		 			}else{
		 				while(size<sequence-1){
			 				listone.add(q);
			 				size = size+1;
		 				}
		 				Question question = sort.getQuestion();
		 				String content = question.getContent();
		 				while(content.contains("<")){
		 					String sub=content.substring(content.indexOf("<"), content.indexOf(">")+1);
		 					content=content.replace(sub,"");
		 				}
		 				content=content.trim();
		 				content=content.replace("\r\n","<w:br/>");
		 				question.setContent(content);
		 				
		 				String A = question.getA();
		 				while(A.contains("<")){
		 					String sub=A.substring(A.indexOf("<"), A.indexOf(">")+1);
		 					A=A.replace(sub,"");
		 				}
		 				A=A.trim();
		 				A=A.replace("\r\n","<w:br/>");
		 				question.setA(A);
		 				
		 				String B= question.getB();
		 				while(B.contains("<")){
		 					String sub=B.substring(B.indexOf("<"), B.indexOf(">")+1);
		 					B=B.replace(sub,"");
		 				}
		 				B=B.trim();
		 				B=B.replace("\r\n","<w:br/>");
		 				question.setB(B);
		 				
		 				String C = question.getC();
		 				while(C.contains("<")){
		 					String sub=C.substring(C.indexOf("<"), C.indexOf(">")+1);
		 					C=C.replace(sub,"");
		 				}
		 				C=C.trim();
		 				C=C.replace("\r\n","<w:br/>");
		 				question.setC(C);
		 				
		 				String D= question.getD();
		 				while(D.contains("<")){
		 					String sub=D.substring(D.indexOf("<"), D.indexOf(">")+1);
		 					D=D.replace(sub,"");
		 				}
		 				D=D.trim();
		 				D=D.replace("\r\n","<w:br/>");
		 				question.setD(D);
		 				listone.add(question);
		 			}
//		 			listone.set(sequence,sort.getQuestion());
		 		}else if(sort.getType().getId() == 2){
		 			int size = listtow.size();
		 			int sequence = sort.getSequence();
		 			Question q = null;
		 			if(size>=sequence){
		 				Question question = sort.getQuestion();
		 				String content = question.getContent();
		 				while(content.contains("<")){
		 					String sub=content.substring(content.indexOf("<"), content.indexOf(">")+1);
		 					content=content.replace(sub,"");
		 				}
		 				content=content.trim();
		 				content=content.replace("\r\n","<w:br/>");
		 				question.setContent(content);
		 				String A = question.getA();
		 				while(A.contains("<")){
		 					String sub=A.substring(A.indexOf("<"), A.indexOf(">")+1);
		 					A=A.replace(sub,"");
		 				}
		 				A=A.trim();
		 				A=A.replace("\r\n","<w:br/>");
		 				question.setA(A);
		 				
		 				String B= question.getB();
		 				while(B.contains("<")){
		 					String sub=B.substring(B.indexOf("<"), B.indexOf(">")+1);
		 					B=B.replace(sub,"");
		 				}
		 				B=B.trim();
		 				B=B.replace("\r\n","<w:br/>");
		 				question.setB(B);
		 				
		 				String C = question.getC();
		 				while(C.contains("<")){
		 					String sub=C.substring(C.indexOf("<"), C.indexOf(">")+1);
		 					C=C.replace(sub,"");
		 				}
		 				C=C.trim();
		 				C=C.replace("\r\n","<w:br/>");
		 				question.setC(C);
		 				
		 				String D= question.getD();
		 				while(D.contains("<")){
		 					String sub=D.substring(D.indexOf("<"), D.indexOf(">")+1);
		 					D=D.replace(sub,"");
		 				}
		 				D=D.trim();
		 				D=D.replace("\r\n","<w:br/>");
		 				question.setD(D);
		 				
		 				listtow.set(sequence-1,question);
		 			}else{
		 				while(size<sequence-1){
		 					listtow.add(q);
			 				size = size+1;
		 				}
		 				Question question = sort.getQuestion();
		 				String content = question.getContent();
		 				while(content.contains("<")){
		 					String sub=content.substring(content.indexOf("<"), content.indexOf(">")+1);
		 					content=content.replace(sub,"");
		 				}
		 				content=content.trim();
		 				content=content.replace("\r\n","<w:br/>");
		 				question.setContent(content);
		 				
		 				String A = question.getA();
		 				while(A.contains("<")){
		 					String sub=A.substring(A.indexOf("<"), A.indexOf(">")+1);
		 					A=A.replace(sub,"");
		 				}
		 				A=A.trim();
		 				A=A.replace("\r\n","<w:br/>");
		 				question.setA(A);
		 				
		 				String B= question.getB();
		 				while(B.contains("<")){
		 					String sub=B.substring(B.indexOf("<"), B.indexOf(">")+1);
		 					B=B.replace(sub,"");
		 				}
		 				B=B.trim();
		 				B=B.replace("\r\n","<w:br/>");
		 				question.setB(B);
		 				
		 				String C = question.getC();
		 				while(C.contains("<")){
		 					String sub=C.substring(C.indexOf("<"), C.indexOf(">")+1);
		 					C=C.replace(sub,"");
		 				}
		 				C=C.trim();
		 				C=C.replace("\r\n","<w:br/>");
		 				question.setC(C);
		 				
		 				String D= question.getD();
		 				while(D.contains("<")){
		 					String sub=D.substring(D.indexOf("<"), D.indexOf(">")+1);
		 					D=D.replace(sub,"");
		 				}
		 				D=D.trim();
		 				D=D.replace("\r\n","<w:br/>");
		 				question.setD(D);
		 				listtow.add(question);
		 			}
		 		}else if(sort.getType().getId() == 3){
		 			int size = listthree.size();
		 			int sequence = sort.getSequence();
		 			Question q = null;
		 			if(size>=sequence){
		 				Question question = sort.getQuestion();
		 				String content = question.getContent();
		 				while(content.contains("<")){
		 					String sub=content.substring(content.indexOf("<"), content.indexOf(">")+1);
		 					content=content.replace(sub,"");
		 				}
		 				content=content.trim();
		 				content=content.replace("\r\n","<w:br/>");
		 				question.setContent(content);
		 				listthree.set(sequence-1,question);
		 			}else{
		 				while(size<sequence-1){
		 					listthree.add(q);
			 				size = size+1;
		 				}
		 				Question question = sort.getQuestion();
		 				String content = question.getContent();
		 				while(content.contains("<")){
		 					String sub=content.substring(content.indexOf("<"), content.indexOf(">")+1);
		 					content=content.replace(sub,"");
		 				}
		 				content=content.trim();
		 				content=content.replace("\r\n","<w:br/>");
		 				question.setContent(content);
		 				listthree.add(question);
		 			}
		 		}else if(sort.getType().getId() == 4){
		 			int size = listfour.size();
		 			int sequence = sort.getSequence();
		 			Question q = null;
		 			if(size>=sequence){
		 				Question question = sort.getQuestion();
		 				String content = question.getContent();
		 				while(content.contains("<")){
		 					String sub=content.substring(content.indexOf("<"), content.indexOf(">")+1);
		 					content=content.replace(sub,"");
		 				}
		 				content=content.trim();
		 				content=content.replace("\r\n","<w:br/>");
		 				question.setContent(content);
		 				listfour.set(sequence-1,question);
		 			}else{
		 				while(size<sequence-1){
		 					listfour.add(q);
			 				size = size+1;
		 				}
		 				Question question = sort.getQuestion();
		 				String content = question.getContent();
		 				while(content.contains("<")){
		 					String sub=content.substring(content.indexOf("<"), content.indexOf(">")+1);
		 					content=content.replace(sub,"");
		 				}
		 				content=content.trim();
		 				content=content.replace("\r\n","<w:br/>");
		 				question.setContent(content);
		 				listfour.add(question);
		 			}
		 		}else if(sort.getType().getId() == 5){
		 			int size = listfive.size();
		 			int sequence = sort.getSequence();
		 			Question q = null;
		 			if(size>=sequence){
		 				Question question = sort.getQuestion();
		 				String content = question.getContent();
		 				while(content.contains("<")){
		 					String sub=content.substring(content.indexOf("<"), content.indexOf(">")+1);
		 					content=content.replace(sub,"");
		 				}
		 				content=content.trim();
		 				content=content.replace("\r\n","<w:br/>");
		 				question.setContent(content);
		 				listfive.set(sequence-1,question);
		 			}else{
		 				while(size<sequence-1){
		 					listfive.add(q);
			 				size = size+1;
		 				}
		 				Question question = sort.getQuestion();
		 				String content = question.getContent();
		 				while(content.contains("<")){
		 					String sub=content.substring(content.indexOf("<"), content.indexOf(">")+1);
		 					content=content.replace(sub,"");
		 				}
		 				content=content.trim();
		 				content=content.replace("\r\n","<w:br/>");
		 				question.setContent(content);
		 				listfive.add(question);
		 			}
		 		}else if(sort.getType().getId() == 6){
		 			int size = listsix.size();
		 			int sequence = sort.getSequence();
		 			Question q = null;
		 			if(size>=sequence){
		 				Question question = sort.getQuestion();
		 				String content = question.getContent();
		 				while(content.contains("<")){
		 					String sub=content.substring(content.indexOf("<"), content.indexOf(">")+1);
		 					content=content.replace(sub,"");
		 				}
		 				content=content.trim();
		 				content=content.replace("\r\n","<w:br/>");
		 				question.setContent(content);
		 				listsix.set(sequence-1,question);
		 			}else{
		 				while(size<sequence-1){
		 					listsix.add(q);
			 				size = size+1;
		 				}
		 				Question question = sort.getQuestion();
		 				String content = question.getContent();
		 				while(content.contains("<")){
		 					String sub=content.substring(content.indexOf("<"), content.indexOf(">")+1);
		 					content=content.replace(sub,"");
		 				}
		 				content=content.trim();
		 				content=content.replace("\r\n","<w:br/>");
		 				question.setContent(content);
		 				listsix.add(question);
		 			}
//		 			listsix.set(sequence,sort.getQuestion());
		 		}else if(sort.getType().getId() == 7){
		 			int size = listserve.size();
		 			int sequence = sort.getSequence();
		 			Question q = null;
		 			if(size>=sequence){
		 				Question question = sort.getQuestion();
		 				String content = question.getContent();
		 				while(content.contains("<")){
		 					String sub=content.substring(content.indexOf("<"), content.indexOf(">")+1);
		 					content=content.replace(sub,"");
		 				}
		 				content=content.trim();
		 				content=content.replace("\r\n","<w:br/>");
		 				question.setContent(content);
		 				listserve.set(sequence-1,question);
		 			}else{
		 				while(size<sequence-1){
		 					listserve.add(q);
			 				size = size+1;
		 				}
		 				Question question = sort.getQuestion();
		 				String content = question.getContent();
		 				while(content.contains("<")){
		 					String sub=content.substring(content.indexOf("<"), content.indexOf(">")+1);
		 					content=content.replace(sub,"");
		 				}
		 				content=content.trim();
		 				content=content.replace("\r\n","<w:br/>");
		 				question.setContent(content);
		 				listserve.add(question);
		 			}
		 		}else if(sort.getType().getId() == 8){
		 			int size = listeight.size();
		 			System.out.println("size的大小是："+size);
		 			int sequence = sort.getSequence();
		 			Question q = null;
		 			if(size>=sequence){
		 				Question question = sort.getQuestion();
		 				String content = question.getContent();
		 				while(content.contains("<")){
		 					String sub=content.substring(content.indexOf("<"), content.indexOf(">")+1);
		 					content=content.replace(sub,"");
		 				}
		 				content=content.trim();
		 				content=content.replace("\r\n","<w:br/>");
		 				question.setContent(content);
		 				listeight.set(sequence-1,question);
		 			}else{
		 				while(size<sequence-1){
		 					listeight.add(q);
			 				size = size+1;
		 				}
		 				Question question = sort.getQuestion();
		 				String content = question.getContent();
		 				while(content.contains("<")){
		 					String sub=content.substring(content.indexOf("<"), content.indexOf(">")+1);
		 					content=content.replace(sub,"");
		 				}
		 				content=content.trim();
		 				content=content.replace("\r\n","<w:br/>");
		 				question.setContent(content);
		 				listeight.add(question);
		 			}
		 		}else if(sort.getType().getId() == 9){
		 			int size = listnine.size();
		 			int sequence = sort.getSequence();
		 			Question q = null;
		 			if(size>=sequence){
		 				Question question = sort.getQuestion();
		 				String content = question.getContent();
		 				while(content.contains("<")){
		 					String sub=content.substring(content.indexOf("<"), content.indexOf(">")+1);
		 					content=content.replace(sub,"");
		 				}
		 				content=content.trim();
		 				content=content.replace("\r\n","<w:br/>");
		 				question.setContent(content);
		 				listnine.set(sequence-1,question);
		 			}else{
		 				while(size<sequence-1){
		 					listnine.add(q);
			 				size = size+1;
		 				}
		 				Question question = sort.getQuestion();
		 				String content = question.getContent();
		 				while(content.contains("<")){
		 					String sub=content.substring(content.indexOf("<"), content.indexOf(">")+1);
		 					content=content.replace(sub,"");
		 				}
		 				content=content.trim();
		 				content=content.replace("\r\n","<w:br/>");
		 				question.setContent(content);
		 				listnine.add(question);
		 			}
		 		}else{
		 			int size = listten.size();
		 			int sequence = sort.getSequence();
		 			Question q = null;
		 			if(size>=sequence){
		 				Question question = sort.getQuestion();
		 				String content = question.getContent();
		 				while(content.contains("<")){
		 					String sub=content.substring(content.indexOf("<"), content.indexOf(">")+1);
		 					content=content.replace(sub,"");
		 				}
		 				content=content.trim();
		 				content=content.replace("\r\n","<w:br/>");
		 				question.setContent(content);
		 				listten.set(sequence-1,question);
		 			}else{
		 				while(size<sequence-1){
		 					listten.add(q);
			 				size = size+1;
		 				}
		 				Question question = sort.getQuestion();
		 				String content = question.getContent();
		 				while(content.contains("<")){
		 					String sub=content.substring(content.indexOf("<"), content.indexOf(">")+1);
		 					content=content.replace(sub,"");
		 				}
		 				content=content.trim();
		 				content=content.replace("\r\n","<w:br/>");
		 				question.setContent(content);
		 				listten.add(question);
		 			}
		 		}		 		
		 	}
		 	
		 	map.put("exam",exam);
		 	map.put("types",types);
		 	map.put("listone",listone);
		 	map.put("listtwo",listtow);
		 	map.put("listhree",listthree);
		 	map.put("listfour",listfour);
		 	map.put("listfive",listfive);
		 	map.put("listsix",listsix);
		 	map.put("listserve",listserve);
		 	map.put("listeight",listeight);
		 	map.put("listnine",listnine);
		 	map.put("listten",listten);
		 
		 	map.put("listvalue",listpointvalue);
		 	map.put("listSumValue",listSumValue);
		 	map.put("listNum",listNum);
		 	map.put("listPointValues",listPointValues);
		 	
		 	
		 	
		 // 提示：在调用工具类生成Word文档之前应当检查所有字段是否完整  
	        // 否则Freemarker的模板殷勤在处理时可能会因为找不到值而报错 这里暂时忽略这个步骤了  
	        File file = null;  
	        InputStream fin = null;  
	        ServletOutputStream out = null;  
	        try {  
	            // 调用工具类WordGenerator的createDoc方法生成Word文档  
	            file = WordGenerator.createDoc(map, "resume");  
	            try {
					fin = new FileInputStream(file);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}  
	              
	            response.setCharacterEncoding("utf-8");  
	            response.setContentType("application/msword");  
	            // 设置浏览器以下载的方式处理该文件默认名为resume.doc  
	            response.addHeader("Content-Disposition", "attachment;filename=resume.doc");  
	            
	            try {
					out = response.getOutputStream();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}  
	            byte[] buffer = new byte[512];  // 缓冲区  
	            int bytesToRead = -1;  
	            // 通过循环将读入的Word文件的内容输出到浏览器中  
	            try {
					while((bytesToRead = fin.read(buffer)) != -1) {  
					    try {
							out.write(buffer, 0, bytesToRead);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}  
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  
	        } finally {  
	            if(fin != null)
					try {
						fin.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}  
	            if(out != null)
					try {
						out.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}  
	            if(file != null) file.delete(); // 删除临时文件  
	        }
	    }  
}
	
