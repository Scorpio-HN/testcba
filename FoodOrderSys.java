package com.cssl.foodlovers.project;

import java.util.Scanner;

/**
 * author:jack
 * time:2020-06-09
 * version:1.2
 *  1.1-1.2 增加了取消订单
 *  哈哈哈
 * */
public class FoodOrderSys {
	public static void main(String[] args) {
		
		System.out.println("show --time");
		//数据主体 （定义多个数组--用来存储  菜单，订单信息）
		
		//订单信息   选择菜品及份数 送餐时间  送餐地址 状态  总金额
		
		String[] names=new String[4];//订餐人姓名
		String[] disMenus=new String[4];//所选的菜品
		int[] times=new int[4];//送餐时间
		String[] addresses=new String[4];//送餐地址
		int[] states=new int[4];//状态-0 已预订  1：已完成
		double[] sumMoney=new double[4];//总金额
		
		
		//初始化 两个订餐信息
		names[0]="张晴";
		disMenus[0]="红烧带鱼 2份";
		sumMoney[0]=76.0;
		times[0]=15;
		addresses[0]="司马里38号 2102教室";
		states[0]=0;//已预订
		
		
		names[1]="刘湘";
		disMenus[1]="红烧蝙蝠 3份";
		sumMoney[1]=900.0;
		times[1]=12;
		addresses[1]="司马里38号 2102教室";
		states[1]=1;
		
		
		//定义数组--存储-菜单  （菜名--单价--点赞数）
		String[] disNames=new String[]{"红烧带鱼","鱼香肉丝","红烧蝙蝠"};//菜名
		double[] prices=new double[]{38.0,25.0,300.0};//单价
		int[] zans=new int[3];//点赞数
		
		
		Scanner input=new Scanner(System.in);
		/*欢迎使用“吃货联盟订餐系统”
		****************************
		1、我要订餐
		2、查看餐袋
		3、签收订单
		4、删除订单
		5、我要点赞
		6、退出系统
		*****************************/
		
		int num=-1;//输入0循环显示菜单
		boolean isExist=false;//是否退出
		System.out.println("欢迎使用\"吃货联盟订餐系统\"");
		
		do{
			System.out.println("****************************");
			System.out.println("1、我要订餐");
			System.out.println("2、查看餐袋");
			System.out.println("3、签收订单");
			System.out.println("4、删除订单");
			System.out.println("5、我要点赞");
			System.out.println("6、退出系统");
			System.out.println("****************************");
			System.out.print("请选择：");
			int choose=input.nextInt();
			boolean isAdd=false;//判断是否还以订餐
			boolean isDel=false;//是否可以删除的标记
			boolean isSing=false;//是否可以签收
			switch (choose) {
				case 1:
					System.out.println("\n**************我要订餐**************");
					for (int i = 0; i < names.length; i++) {
						if(names[i]==null){//找到名字数组中第一个空位，添加新的名字
							isAdd=true;//标记--是否可以添加新的菜单
							System.out.print("请输入订餐人姓名：");
							String name=input.next();
							
							System.out.println("序号\t菜名\t单价");
							//循环显示菜单信息 （3个）
							for (int j = 0; j < disNames.length; j++) {
								String price=prices[j]+"元";
								String zs=zans[j]==0?"":zans[j]+"赞";
							    System.out.println((j+1)+"\t"+disNames[j]+"\t"+price+"\t"+zs);
							
							}
							System.out.print("请选择点菜的编号：");
							int chooseDisMenu=input.nextInt();
							System.out.println("请输入菜的份数：");
							int chooseDisMenuNum=input.nextInt();
							//取出 菜名+“”+份
							String disMenu=disNames[chooseDisMenu-1]+" "+chooseDisMenuNum+"份";
							//金额=单价*数量
							double sumprice=prices[chooseDisMenu-1]*chooseDisMenuNum;
							//订餐费用超过50 免送餐费 ，否则+6
							double songMoney=sumprice>50?0:6;
							//全部费用 =消费金额+送餐费
							double sumCost=sumprice+songMoney;
							System.out.print("请输入送餐时间：（10-20整点数）");
							int timeNum=input.nextInt();
							while(timeNum<10||timeNum>20){
								System.out.println("你输入的时间不对，请重新输入（10-20整点数）:");
								timeNum=input.nextInt();
							}
							System.out.print("请输入送餐地址：");
							String address=input.next();
							
							System.out.println("订餐成功！！！！");
						
							System.out.println("您订的是："+disMenu);
							System.out.println("送餐时间："+timeNum+"点");
							System.out.println("餐费:"+sumprice+",送餐费:"+songMoney+",总计："+sumCost);
							
							
							
							names[i]=name;//将订餐人姓名存入数组
							disMenus[i]=disMenu; //菜名+份数
							times[i]=timeNum;//送餐时间
							addresses[i]=address;//送餐地址
							sumMoney[i]=sumCost;//总消费金额
							break;
						}
						
					}
					if(!isAdd){
						System.out.println("袋子满了，不能订餐了");
					}
					break;
				case 2:
					System.out.println("\n**************查看餐袋**************");
					System.out.println("序号\t订餐人\t餐品信息\t\t送餐日期\t\t\t送餐地址\t\t总金额\t订单状态");
					for (int i = 0; i < names.length; i++) {
						if(names[i]!=null){//数组元素不为空，才输出
							String sta=states[i]==0?"已预订":"已完成";
							System.out.println((1+i)+"\t"+names[i]+"\t"+disMenus[i]+"\t"+times[i]+"\t\t"+addresses[i]+"\t\t"+sumMoney[i]+"\t"+sta);
						}
					}
					break;
				case 3:
					System.out.println("\n**************签收订单**************");
					System.out.print("请选择你要签收的单号：");
					int orderNum=input.nextInt();
					for (int i = 0; i < names.length; i++) {
						//签收：1.，2.3.	
						//订单处于预订状态可以签收	
						if(names[i]!=null&&states[i]==0&&i==orderNum-1){
							states[i]=1;//签收了
							System.out.println("签收成功了....");
							isSing=true;
							
							//订单已经完成  
						}else if(names[i]!=null&&states[i]==1&&i==orderNum-1){
							System.out.println("订单已经完成，不能再次签收！！！");
							isSing=true;
						}
						
					
					}
					//订单号不存在 订单号不存在
					if(!isSing){
						System.out.println("订单号不存在,不能签收");
					}
					
					
					break;
				case 4:
					System.out.println("\n**************删除订单**************");
					System.out.println("序号\t订餐人\t餐品信息\t\t送餐日期\t\t\t送餐地址\t\t总金额\t订单状态");
					for (int i = 0; i < names.length; i++) {
						if(names[i]!=null){//数组元素不为空，才输出
							String sta=states[i]==0?"已预订":"已完成";
							System.out.println((1+i)+"\t"+names[i]+"\t"+disMenus[i]+"\t"+times[i]+"\t\t"+addresses[i]+"\t\t"+sumMoney[i]+"\t"+sta);
						}
					}
					System.out.print("请输入您要删除订单编号:");
					int delId=input.nextInt();
					for (int i = 0; i < names.length; i++) {
						//元素不能为空，并且订单状态处于已完成，并且输入编号能匹配元素角标
						if(names[i]!=null&&states[i]==1&&i==delId-1){
							isDel=true;
							
							//删除，将后面元素覆盖前面元素，最后一个元素赋值为null
							for (int j = i; j < names.length-1; j++) {
								names[j]=names[j+1];
								disMenus[j]=disMenus[j+1];
								times[j]=times[j+1];
								addresses[j]=addresses[j+1];//送餐地址
								sumMoney[j]=sumMoney[j+1];
							}
							//清空最一位元素
							names[names.length-1]=null;
							disMenus[names.length-1]=null;
							times[names.length-1]=0;
							addresses[names.length-1]=null;//送餐地址
							sumMoney[names.length-1]=0;
							System.out.println("删除成功了！！！！");
							break;
							
						}else if(names[i]!=null&&states[i]==0&&i==delId-1){
							System.out.println("你的订单未签收，暂时不能删除");
							isDel=true;
							break;
						}
					}
					
					if(!isDel){
						System.out.println("你删除的订单不存在，不能删除");
					}
					break;
				case 5:
					System.out.println("\n**************我要点赞**************");
					System.out.println("序号\t菜名\t单价\t点赞数");
					//循环显示菜单信息 （3个）
					for (int j = 0; j < disNames.length; j++) {
						String price=prices[j]+"元";
						String zs=zans[j]==0?"":zans[j]+"赞";
					    System.out.println((j+1)+"\t"+disNames[j]+"\t"+price+"\t"+zs);
					
					}
					System.out.print("请输入您要点赞的菜编号：");
					
					int zanNum=input.nextInt();
					
					zans[zanNum-1]++;//点赞数++
					System.out.println("点赞成功");
					
					break;
				case 6:
					//System.out.println("退出系统");
					isExist=true;
					break;

				default:
					isExist=true;
					break;
				}
			
			if(!isExist){
				System.out.print("输入0返回：");
				num=input.nextInt();
			}else{
				break;
			}
			
		}while(num==0);
		System.out.println("谢谢使用，退出系统");
		
	}

}
