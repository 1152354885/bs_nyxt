#ifndef __LED_H
#define __LED_H	 
#include "sys.h"

#define LED0 PBout(5)// 宏定义LED0为GPIOB.5输出
#define LED1 PEout(5)// 宏定义LED1为GPIOE.5输出

void LED_Init(void);//初始化

		 				    
#endif
