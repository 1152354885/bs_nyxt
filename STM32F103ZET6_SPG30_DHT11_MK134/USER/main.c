#include "delay.h"
#include "sys.h"
#include "usart.h"
#include "led.h"
#include "timer.h"
#include "key.h"
#include "SGP30.h"
#include "string.h"
#include "stdlib.h"
#include "dht11.h"
#include "lsens.h"


#include "math.h"
#include "stdio.h"
u8 temperature;
u8 humidity; 
u8 adcx; 
u8 key=1;
u32 dat;
u16 pm,co2Data,TVOCData;	
//char adcdata[]="LSEN:     ";
u16 pwmval=0;
u8 dir=1;
//char adcdata[256];
void menu1();
void menu();
void menu2();
char a=1;

 int main(void)
 {		
	u8 *b;
	u8 t=0;   
	u32 dat;
	u16 pm,co2Data,TVOCData;	 
  u8 temperature;       
  u8 humidity;
	u8 adcx; 
	//char adcdata[]="LSEN:     ";
	int i;
	delay_init();	    	 			//ÑÓÊ±º¯Êý³õÊ¼»¯	  
	NVIC_PriorityGroupConfig(NVIC_PriorityGroup_2); 			//ÉèÖÃNVICÖÐ¶Ï·Ö×é2:2Î»ÇÀÕ¼ÓÅÏÈ¼¶£¬2Î»ÏìÓ¦ÓÅÏÈ¼¶
	uart_init(115200);	 //´®¿Ú³õÊ¼»¯Îª115200
	SGP30_Init();  
	//usart3_init(115200);	 				//´®¿Ú³õÊ¼»¯Îª115200
  KEY_Init();		
	//esp8266_start_trans();							//esp8266½øÐÐ³õÊ¼»
	LED_Init();
	DHT11_Init();
	Lsens_Init(); 
	  delay_ms(800);
	  printf("Start!\r\n");
	//esp8266_start_trans();	
  

//  
// 	while(1)
//		{
//		

//    
////			DHT11_Read_Data(&temperature,&humidity);
//		
//	//   delay_ms(5000);

//				 adcx= Lsens_Get_Val();
//	
//		adcdata[6]=(char)(adcx)/10%10+48;//ÏÔÊ¾Ê®Î»Êý
//		adcdata[7]=(char)(adcx)%10+48;//ÏÔÊ¾¸öÎ»Êý
//		//	delay_ms(5000);
//    	u3_printf("%s\n\r",adcdata);
//			DHT11_Read_Data(&temperature,&humidity);	
//					delay_ms(5000);
////		printf("temp£º%d C \nhumi£º%d %% \n\r",temperature,humidity);
////  
//	u3_printf("temp is %d C\n\n humi is %d %% \n\n",temperature,humidity);
////				delay_ms(1000);
//			 if(temperature>30){
//		 LED1=0;
//		 u3_printf("Dear bailu, it's time to turn on the air conditioner\n\r");
//	 }else if(temperature<20){
//		 LED1=1;
//		 u3_printf("Dear bailu, don't catch cold, take care of yourself\n\r");
//	 }
////	 }else{

////    printf("Dear , have a good day!\n\n\r");
////	 }		 


// if(strcmp(adcdata,"LSEN: 80")>0){
//		 LED0=0;
//	 delay_ms(1000);
//		 u3_printf("Dear bailu,You're always shining!\n\n");
//	 }else if(strcmp(adcdata,"LSEN: 20")<0){
//		 LED0=1;
//		 delay_ms(1000);
//		 u3_printf("Dear bailu,please let me light you up in the dark\n\n");
//	 }






//	

//			
//			
//			
// }
//	
//	delay_ms(500);
// 
//}
	while(1)
		{
		

    
		  DHT11_Read_Data(&temperature,&humidity);
		  adcx= Lsens_Get_Val();
			LED1=1;
			SGP30_ad_write(0x20,0x08);
			dat = SGP30_ad_read();
			co2Data = (dat  & 0xffff0000) >> 16;
			TVOCData = dat & 0x0000ffff;		
//		printf("TVOC:%d Ppb,co2:%d Ppm\r\n",TVOCData,co2Data);
//	
//		adcdata[6]=(char)(adcx)/10%10+48;//ÏÔÊ¾Ê®Î»Êý
//		adcdata[7]=(char)(adcx)%10+48;//ÏÔÊ¾¸öÎ»Êý
//		//	delay_ms(5000);
 //   	printf("%d\r\n",adcx);
		//	printf("%s\r\n",adcdata);
		//	DHT11_Read_Data(&temperature,&humidity);	
			//	delay_ms(800);
			printf("{\"temp\":\"%d\",\"humi\":\"%d\",\"light\":\"%d\",\"co2\":\"%d\"}\r\n",temperature,humidity,adcx,co2Data);//jsongeshi
			  
	//			printf("temp \r\n");
//  
//	u3_printf("temp is %d C\n\n humi is %d %% \n\n",temperature,humidity);
				delay_ms(800);
		
				 if(adcx<30){
						 LED0=0;
					 delay_ms(100);
						// u3_printf("Dear bailu,You're always shining!\n\n");
					 }else if(adcx>30){
						 LED0=1;
						 delay_ms(100);
						// u3_printf("Dear bailu,please let me light you up in the dark\n\n");
					 }





	

//			
//			
//			
      }
	
	//delay_ms(500);
 
}

