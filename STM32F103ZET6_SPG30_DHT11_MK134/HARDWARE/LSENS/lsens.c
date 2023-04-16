#include "lsens.h"
#include "delay.h"

 //PF8 anolog输入
//初始化光敏传感器
void Lsens_Init(void)
{
	GPIO_InitTypeDef  GPIO_InitStruct;
	
	RCC_APB2PeriphClockCmd(RCC_APB2Periph_GPIOF,ENABLE);
	
	GPIO_InitStruct.GPIO_Mode=GPIO_Mode_AIN;
	GPIO_InitStruct.GPIO_Pin=	GPIO_Pin_8;
	GPIO_Init(GPIOF,&GPIO_InitStruct);
	
	Adc3_Init( );
}
//读取Light Sens的值
//0~100:0,最暗;100,最亮 
u8 Lsens_Get_Val(void)
{
	u32 temp_val=0;
	u8 t;
	for(t=0;t<LSENS_READ_TIMES;t++)
	{
		temp_val+=Get_Adc3(LSENS_ADC_CHX);	//读取ADC值
		delay_ms(5);
	}
	temp_val/=LSENS_READ_TIMES;//得到平均值 
	if(temp_val>4000)temp_val=4000;
	return (u8)(100-(temp_val/40));
}

void send_str(char *str)//发送字符串
{
	while(*str != '\0')
	{
		USART_SendData(USART1,*str);//发送一个字节的数据以后
		while( USART_GetFlagStatus(USART1,USART_FLAG_TC) == RESET);//等待数据发送完成
		str++;
	}
}











