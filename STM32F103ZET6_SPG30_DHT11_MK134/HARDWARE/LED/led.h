#ifndef __LED_H
#define __LED_H	 
#include "sys.h"

#define LED0 PBout(5)// �궨��LED0ΪGPIOB.5���
#define LED1 PEout(5)// �궨��LED1ΪGPIOE.5���

void LED_Init(void);//��ʼ��

		 				    
#endif
